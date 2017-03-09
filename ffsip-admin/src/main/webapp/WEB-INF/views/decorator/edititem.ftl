<#assign sitemesh=JspTaglibs["http://www.opensymphony.com/sitemesh/decorator"] />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><@sitemesh.title /> - 非凡之星管理平台</title>
	
	<#include "../common/css.ftl" encoding="utf-8"> 

	<!-- 可选样式  strat -->
	<link href="${BasePath !}/asset/js/control/validation/css/validation.css?v=${ver !}" rel="stylesheet">
	<!-- 可选样式  end -->
	
	<#include "../common/js.ftl" encoding="utf-8">  
	
	<!-- 可选js  strat -->
	<script src="${BasePath !}/asset/js/control/validation/jquery.validate.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/js/control/validation/localization/messages_zh.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/js/control/validation/validationSetDefaults.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/js/control/validation/validationUtils.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/js/control/validation/additionalMethods.js?v=${ver !}" type="text/javascript"></script>
	<!-- 可选js  strat -->
	
	<@sitemesh.head />
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<div class="box-body">	
			<@sitemesh.body />
		</div>
	</div>
</div>
</body>
</html>