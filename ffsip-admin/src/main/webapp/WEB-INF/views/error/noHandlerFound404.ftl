<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>404 - 页面不存在</title>
	<link rel="stylesheet" href="${BasePath !}/asset/v2/ui/css/bootstrap.css" media="screen" >
	<link rel="stylesheet" href="${BasePath !}/asset/v2/ui/css/style-inner.css" >
	<script src="${BasePath !}/asset/js/jquery-1.10.2.min.js"></script>
</head>
<body>

	<div class="error-page">
		<h2 class="headline text-yellow"> 404</h2>
	
		<div class="error-content">
		  <h3><span class="glyphicon glyphicon-warning-sign text-yellow"></span> 呃！该页面未找到</h3>
	
		  <p>
			你所访问的页面不存在！<br />
			建议访问 <a href="###dashboard.html">Dashboard</a>，或者 <a href="javascript:history.go(-1);">返回</a>，或者搜索本站
		  </p>
	
		  <form class="search-form">
			<div class="input-group">
			  <input name="search" class="form-control" placeholder="搜索..." type="text">
	
			  <div class="input-group-btn">
				<button type="submit" name="submit" class="btn btn-warning btn-flat"><span class="glyphicon glyphicon-search"></span>
				</button>
			  </div>
			</div>
			<!-- /.input-group -->
		  </form>
		</div>
		<!-- /.error-content -->
	</div>
	
	<#include "../common/loadingffzx.ftl" encoding="utf-8"> 
	<@load_content content="small"/>
</body>
</html>