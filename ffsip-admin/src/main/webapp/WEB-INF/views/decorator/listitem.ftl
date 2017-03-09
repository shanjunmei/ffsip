<#assign sitemesh=JspTaglibs["http://www.opensymphony.com/sitemesh/decorator"] /> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><@sitemesh.title /> - 非凡之星管理平台</title>
	
	<#include "../common/css.ftl" encoding="utf-8"> 
	
	<#include "../common/js.ftl" encoding="utf-8"> 
	
    <!-- 时间控件 -->
    <script src="${BasePath !}/asset/js/control/My97DatePicker/WdatePicker.js?v=${ver !}" type="text/javascript"></script>
    <script src="${BasePath !}/asset/js/common/commonitem.js?v=${ver !}"></script>

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