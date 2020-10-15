var App = function () {
    var _masterCheckbox;
    var _checkbox;

    // 用于存放id的数组
    var _idArray;

    //默认的Dropzone对象
    var defaultDropzoneOpts = {
        url:"",
        paramName: "dropzFile",
        dictDefaultMessage: "拖动或点击文件上传",
    }

    /*
    私有方法
     */
    var handlerInitCheckbox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        })
        // 获取控制CheckBox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        // 获取全部checkBox集合
        _checkbox = $('input[type="checkbox"].minimal');

    };

    /*
    CheckBox全选功能
     */
    var handlerCheckBoxAll = function () {
      // 绑定事件
        _masterCheckbox.on("ifClicked", function (e) {
            // 返回true表示未选中
            if(e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            // 选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var handlerDeleteMulti = function(url) {
        _idArray = new Array();

        // 将选中的元素id放入数组
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确定删除数据吗");
        }

        // 点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        // 如果用户点击删除按钮时，调用删除方法
        $("#btnModalOk").bind("click", function () {
            del();
        });

        /**
         * 当前私有函数的私有函数
         * 删除数据
         */
        function del() {
            $("#modal-default").modal('hide');
            // 如果没有选择数据项，则关闭模态框
            if (_idArray.length === 0) {
                // ...
            }
            // 删除操作
            else {
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "data": {"ids":_idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            // 删除请求完成后，无论删除成功还是失败，都需要弹出模态框进行提示，所以这里需要解绑原来的click事件
                            $("#btnModalOk").unbind("click");
                            // 删除成功
                            if (data.status === 200) {
                                // 请求成功，刷新页面
                                $("#btnModalOk").bind("click", function () {
                                    window.location.reload();
                                });

                            }

                            // 删除失败
                            else {
                                // 请求失败，隐藏模态框
                                $("#btnModalOk").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            // 无论成功失败，必须显示模态框
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);

            }
        }

    };

    /**
     * 初始化文件上传
     * @param url
     */
    var handlerInitDropz = function (opts) {
        Dropzone.autoDiscover = false; // 关闭Dropzonez自动发现功能
        $.extend(defaultDropzoneOpts, opts);  //defaultDropzoneOpts集成opts

        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    }

    /**
     * 初始化DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            'processing': true,
            'searching': false,
            'serverSide': true,
            "deferRender": true,
            'ajax': {
                "url": url,
                contentType: 'text/json,charset=utf-8',

            },
            "columns":columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            'drawCallback': function (settings) {
                handlerInitCheckbox();
                handlerCheckBoxAll();
            }

        });
        return _dataTable;
    };

    /**
     * 初始化ZTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam,  // 当在前端点击已出现的类目内容时，前端会异步自动传递id到后端。
            }
        };
        $.fn.zTree.init($("#Mytree"), setting);
        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("Mytree");
            var nodes = zTree.getSelectedNodes();

            // 未选择
            if(nodes.length==0){
                alert("请选择一个节点");
            }
            // 已选择
            else {
                callback(nodes);
            }
        })
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        // 通过Ajax请求的方式，将jsp装载进模态框
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                // 把detail页面请求到，放入模态框
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }

    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCheckbox();
            handlerCheckBoxAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },
        /**
         * 初始化DataTables
         * @param url
         * @param columns
         * @returns {*|jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 显示详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        /**
         * 初始化ZTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback) {
            handlerInitZTree(url, autoParam, callback);
        },

        initDropzone: function (opts) {
            handlerInitDropz(opts);
        }
    }
}();

$(document).ready(function () {
    App.init();
});
