<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="${BasePath !}/asset/js/common/html5shiv.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/js/common/respond.min.js?v=${ver !}"></script>
<![endif]-->

<!-- 公共样式 start  -->
<link rel="stylesheet" href="${BasePath !}/asset/css/base.min.css?v=${ver !}">
<link rel="stylesheet" href="${BasePath !}/asset/js/control/artDialog-6.0.4/css/ui-dialog.css?v=${ver !}">
<link rel="stylesheet" href="${BasePath !}/asset/css/style.css?v=${ver !}">
<!-- 公共样式  end -->

<!-- 当前项目的全局样式 -->
<#if sys ??>
<link rel="stylesheet" href="${BasePath !}/asset/css/${sys !}.css?v=${ver !}">
</#if>