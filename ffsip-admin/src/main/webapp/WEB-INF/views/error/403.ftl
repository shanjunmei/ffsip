<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>403 - 操作权限不足</title>
	<link rel="stylesheet" href="${BasePath !}/asset/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!--403页面--start -->
<div class="operationFailed">
    <div class="icon-failed"><img src="${BasePath !}/asset/img/Lock.png"> </div>
    <div class="operationFailedText">
        <h3>没有权限操作！</h3>
        <p>如需操作，请联系管理员！<!-- 访问权限：${permissionStr!} --></p>
    </div><div class="clearfix"></div>
    <div class="backUpper backUpper2"><a href="javascript:" onclick="history.go(-1);"><< 返回上一级</a> </div>
</div>
<!--403页面--end-->
<#include "../common/css.ftl" encoding="utf-8"> 
<#include "../common/js.ftl" encoding="utf-8">  
<#include "../common/loadingffzx.ftl" encoding="utf-8"> 
<@load_content content="small"/>
</body>
</html>