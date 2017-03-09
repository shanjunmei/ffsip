<#include "global.ftl" encoding="utf-8">

<!DOCTYPE html>
<html class="${sys !} ${mod !}">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>非凡之星管理平台</title>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${BasePath !}/asset/js/common/html5shiv.min.js?v=${ver !}"></script>
        <script src="${BasePath !}/asset/js/common/respond.min.js?v=${ver !}"></script>
    <![endif]-->
	
	<link rel="stylesheet" href="${BasePath !}/asset/css/index.css?v=${ver !}">
	<!-- <link rel="stylesheet" href="${BasePath !}/asset/css/style.css?v=${ver !}"> -->
    <link rel="stylesheet" href="${BasePath !}/asset/js/control/artDialog-6.0.4/css/ui-dialog.css?v=${ver !}"> 
	<!-- 可选样式  strat -->
	<link href="${BasePath !}/asset/js/control/pagetab/css/jquery.pagetab.css?v=${ver !}" rel="stylesheet">
	<link href="${BasePath !}/asset/v2/ui/css/bootstrap.css?v=${ver !}" rel="stylesheet">
	<link href="${BasePath !}/asset/v2/ui/css/style.css?v=${ver !}" rel="stylesheet">
	<!-- 可选样式  end -->	
	
	<!-- index js -->
	<script src="${BasePath !}/asset/js/jquery-1.10.2.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/js/control/bootstrap/js/bootstrap.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/js/control/ajax/js/ajaxUtils.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/js/common/common.v2.js?v=${ver !}"></script>	
	<script src="${BasePath !}/asset/js/control/artDialog-6.0.4/dist/dialog-plus.js?v=${ver !}"></script>
    <script src="${BasePath !}/asset/js/control/artDialog-6.0.4/dialogUtils.js?v=${ver !}"></script>
	<script type="text/javascript" src="${BasePath !}/asset/js/common/pageadm.js?v=${ver !}"></script>
	<script type="text/javascript">
		var rootPath = "${BasePath !}";
		var version = "${ver !}";
	</script>
	<!-- index js end -->
	
	<!-- 可选js  strat -->
	<script src="${BasePath !}/asset/js/control/pagetab/js/jquery.pagetab.js?v=${ver !}"></script>
	<!-- 可选js  strat -->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="row wrapper">

		<#include "header.ftl" encoding="utf-8"> 
	
		<#include "menu.ftl" encoding="utf-8">
	
		<#include "content.ftl" encoding="utf-8">  
	
		<#include "foot.ftl" encoding="utf-8">
		
		<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->
	<!-- AdminLTE App -->
	
	<script src="${BasePath !}/asset/js/control/dist/js/app.min.js?v=${ver !}"></script>	
	<input type="hidden" name="ctx" id="ctx" value="${BasePath !}"/>
	<script src="${BasePath !}/asset/js/index.v2.js?v=${ver !}"></script>
	
<!-- 以下是覆盖一下密码修改dialog的button样式 -->
<style>
.art-dialog-footer button {
  color: #fff !important;
  background-color: #337ab7 !important;
  border-color: #2e6da4 !important;
}
.art-dialog-footer button:focus,
.art-dialog-footer button.focus {
  color: #fff;
  background-color: #286090 !important;
  border-color: #122b40 !important;
}
.art-dialog-footer button:hover {
  color: #fff !important;
  background-color: #286090 !important;
  border-color: #204d74 !important;
}
</style>
</body>
</html>