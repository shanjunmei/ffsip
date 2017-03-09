<!DOCTYPE html>
<html class="p-error">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>exception - 系统内部错误</title>
	<link rel="stylesheet" href="${BasePath !}/asset/js/control/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${BasePath !}/asset/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!--500页面--start-->
	<div class="operationFailed operationFailed2">
		<div class="operationFailedText">
			<div class="error-header text-center">		
				<img src="${BasePath !}/asset/img/scission.png">
				<h3 class="text-center">exception 系统内部错误！</h3>
			</div>
			<table class="table">
				<tr>
					<td class="td-left">错误编号：</td>
					<td class="td-right">${errorCode !}</td>
				</tr>
				<tr>
					<td class="td-left">错误信息：</td>
					<td class="td-right">${(exception.message) !}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center noPaddingLR">
						<button type="button" class="btn btn-info btn-block btn-lg btn-show-error" id="btn_show_error">查看详细信息</button>
						<h5>请点击“查看详细信息”按钮，将详细错误信息发送给系统管理员，谢谢！</h5>
					</td>
				</tr>
			</table>		
		</div>
	</div>
	
	<div id="error_detail" style="display:none;">
		<textarea class="form-control">${errorStackTrace !}</textarea>
	</div>
	
	<div class="clearfix"></div>
	
    <div class="text-center" style="margin-bottom:40px;">
        <a href="javascript:" onclick="history.go(-1);" class="btn btn-link btn-lg"><< 返回上一页</a>
    </div>

<script>
	var btnShowError = document.getElementById('btn_show_error');

	btnShowError.onclick = function(){
		var tr = document.getElementById('error_detail');
		
		if (tr.style.display == 'none') {
			tr.style.display = 'block';
			btnShowError.innerText = '关闭详细信息';
		} else {
			tr.style.display = 'none';
			btnShowError.innerText = '查看详细信息';
		}		
	}
</script>
<!--500页面--end-->
<#include "../common/css.ftl" encoding="utf-8"> 
<#include "../common/js.ftl" encoding="utf-8">  
<#include "../common/loadingffzx.ftl" encoding="utf-8"> 
<@load_content content="small"/>
</body>
</html>