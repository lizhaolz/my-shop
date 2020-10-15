<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/zTree_v3-master/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone-5.7.0/dist/min/dropzone.min.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone-5.7.0/dist/min/basic.min.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">
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
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id==null ? "新增" : "编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group ">
                                    <label  class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id"/>
                                        <input id="categoryName" class="form-control required " placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default" value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <!-- path 默认path和id值相等-->
                                        <form:input path="title" cssClass="form-control required " placeholder="标题" />
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required " placeholder="子标题" />
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control" placeholder="标题描述" />
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control" placeholder="超链接" />
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="pic" class="col-sm-2 control-label">图片</label>
                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control" readonly="true" placeholder="图片" />
                                        <div id="dropz" class="dropzone">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control" placeholder="图片2" />
                                        <div id="dropz2" class="dropzone">
                                    </div>
                                </div>
                                <div>
                                    <label class="col-sm-2 control-label">内容</label>
                                    <div class="col-sm-10">

                                        <form:hidden path="content"/>
                                        <div id="editor">
                                            ${tbContent.content}
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" id="btnSubmit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>

</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/zTree_v3-master/js/jquery.ztree.core.min.js"></script>
<script src="/static/assets/plugins/dropzone-5.7.0/dist/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<!-- 自定义模态框 -->
<sys:modal title="请选择" msg="<ul id='Mytree' class='ztree'></ul>"/>
<script>
    $(function () { // 初始化方法
        App.initZTree("/content/category/tree/data", ["id"], function (nodes) {
            var node = nodes[0];
            $("#categoryName").val(node.name);

            $("#categoryId").val(node.id);
            $("#modal-default").modal("hide");
        });

        initwangEditor();
    });
    function initwangEditor(){
        // 富文本编辑器
        var E = window.wangEditor;
        var editor = new E("#editor");
        editor.customConfig.uploadImgServer = '/upload';
        editor.customConfig.uploadFileName = "editFile";
        editor.create();
        $("#btnSubmit").bind("click", function () { // 给提交按钮绑定事件，在提交时将富文本编辑器的内容写入content字段
            var contenthtml = editor.txt.html();
            $("#content").val(contenthtml);
            //return false; 可以阻止submit提交
        })
    }
    App.initDropzone({
        id:"#dropz",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic").val(data.fileName);
            });
        }
    });
    App.initDropzone({
        id:"#dropz2",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic2").val(data.fileName);
            });
        }
    })

</script>

</body>
</html>

