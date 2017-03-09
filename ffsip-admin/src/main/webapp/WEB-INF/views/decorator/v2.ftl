<#assign sitemesh=JspTaglibs["http://www.opensymphony.com/sitemesh/decorator"] />

<#include "../global.ftl" encoding="utf-8">

<!DOCTYPE html>
<html class="${sys !} ${mod !}">
<head>
	<meta charset="utf-8">
	<title><@sitemesh.title /> - 非凡之星管理平台</title>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="${BasePath !}/asset/js/common/html5shiv.min.js?v=${ver !}"></script>
		<script src="${BasePath !}/asset/js/common/respond.min.js?v=${ver !}"></script>
	<![endif]-->

	<!-- v2/ui -->
	<link rel="stylesheet" href="${BasePath !}/asset/css/font-awesome.min.css?v=${ver !}">
	<link rel="stylesheet" href="${BasePath !}/asset/v2/ui/css/bootstrap.css?v=${ver !}">
	<!-- 
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">	
	<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.blue-pink.min.css" />
	-->
	<link  rel="stylesheet" href="${BasePath !}/asset/v2/ui/css/style-inner.css?v=${ver !}" >	
	<link href="${BasePath !}/asset/v2/css/custom.css?v=${ver !}" rel="stylesheet" >	
	<!-- 公共样式  end -->
		
	<!-- 公共js  strat -->
	<script src="${BasePath !}/asset/js/jquery-1.10.2.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/v2/js/require/require.js?v=${ver !}"></script>
	<!-- 
		https://github.com/requirejs/xrayquire 
		xrayquire.showTree();
		xrayquire.showCycles();
	-->
	<script>
		if (location.search.indexOf('xray') !== -1) {
			document.write('<' + 'script src="${BasePath !}/asset/v2/js/util/xrayquire.js?v=${ver !}"></' + 'script>');
		}
	</script>
	<script src="${BasePath !}/asset/js/control/bootstrap/js/bootstrap.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/js/control/ajax/js/ajaxUtils.js?v=${ver !}" type="text/javascript"></script>
	<script src="${BasePath !}/asset/v2/ui/js/material.min.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/js/common/common.v2.js?v=${ver !}"></script>

	<!-- v2/ui -->
	<script src="${BasePath !}/asset/v2/ui/js/common-inner.js?v=${ver !}"></script>

	<!-- 公共js  end -->
	<script type="text/javascript" src="${BasePath !}/asset/js/common/pageadm.js?v=${ver !}"></script>
	<script src="${BasePath !}/asset/v2/js/util/util.js?v=${ver !}"></script>
</head>
<body>
	<div class="container-fluid row">
		<@sitemesh.body />
	</div>
	
	<@load_content content="small"/>  
</body>
</html>