<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <!--保存用户成功时提示-->
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                            <div class="row" style="margin-top: 20px;">
                                <div class="col-xs-12">
                                    <a href="/content/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus">新增</i></a>&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMulti('/content/delete')"><i class="fa fa-trash-o">删除</i></button>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download">导入</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload">导出</i></a>&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display') == 'none'? $('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa fa-search">搜索</i></button>
                                </div>
                            </div>
                            <div class="row box-info-search form-horizontal" style="margin-top: 20px; display: none;">
                                <div class="col-xs-12">
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label for="title" class="col-sm-2 control-label">标题</label>
                                            <div class="col-sm-8">
                                                <input id="title" class="form-control" placeholder="标题"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label for="subTitle" class="col-sm-3 control-label">子标题</label>
                                            <div class="col-sm-8">
                                                <input id="subTitle" class="form-control" placeholder="子标题"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>
                                            <div class="col-sm-8">
                                                <input id="titleDesc" class="form-control" placeholder="标题描述"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" style="padding-right: 84px;">
                                        <div class="col-xs-12">
                                            <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>


                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<!-- 自定义模态框 -->
<sys:modal/>

<script>
    var _dataTable;
    $(function () {
        var _columns = [{"data": function (row, type, value, meta) {
                return '<input id="'+row.id + '" type="checkbox" class="minimal"/>';
            }
        },
            {"data": "id"},
            {"data": "tbContentCategory.name"},
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {"data": function (row, type, value, meta) {
                    if(row.url==null){
                        return '';
                    }
                    return '<a href="' + row.url + '" target = "_blank">查看</a>'
                }},
            {"data": function (row, type, value, meta) {
                    if(row.pic==null){

                        return '';
                    }
                    console.log(row.pic);
                    return '<a href="' + row.pic + '" target = "_blank">查看</a>'
                }},
            {"data": function (row, type, value, meta) {
                    if(row.pic2==null){
                        return '';
                    }
                    return '<a href="' + row.pic2 + '" target = "_blank">查看</a>'
                }},
            {"data": function(row, type, value, meta){
                return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                }},
                {"data": function (row, type, value, meta) {
                    var detailUrl = "/content/detail?id=" + row.id;
                    // 注意这个的detailUrl是个字符串参数，所以要用单引号包裹起来
                    // 转义字符的单引号是为了把他当成字符串参数往外传的
                    return '<button type="button" class="btn btn-sm btn-default" onclick="App.showDetail(\''+ detailUrl +'\');"><i class="fa fa-search">查看</i></button>&nbsp;&nbsp;&nbsp;'+
                        '<a href="/content/form?id=' + row.id + ' "type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit">编辑</i> </a>&nbsp;&nbsp;&nbsp;' +
                        '<a href="#" type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash-o">删除</i></a>&nbsp;&nbsp;&nbsp;';
                }
            }];
        _dataTable = App.initDataTables("/content/page", _columns);
    });
    function search() {
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();
        var param = {
            "title": title,
            "subTitle": subTitle,
            "titleDesc": titleDesc,
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>

