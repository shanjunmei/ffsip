<!DOCTYPE html>
<html class="p-error">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>提示</title>
	<link rel="stylesheet" href="${BasePath !}/asset/js/control/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${BasePath !}/asset/css/style.css">
<#include "../common/css.ftl" encoding="utf-8"> 
<#include "../common/js.ftl" encoding="utf-8">  
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div >
		<div  id="infoContent" style="display:none" title="<#if infoTitle??>${(infoTitle)!}<#else>提示</#if>">
				<div ><h3>${infoMassage !}</h3></div>
				<script>
						requirejs(['jq/dialog'], function() {
						$('#infoContent').dialog({
							modal: true,
							open: function (event, ui) {
               			  		$(".ui-dialog-titlebar-close", $(this).parent()).hide();
            				},
            				buttons:{
            					"确定":function(){
            						$(this).dialog('close');
            						window.history.go(-1);
            						/*
            						var p=window.parent;
            						if(!p){
            							p=window;
            						}
            						p.location.reload();
            						*/
            					}
            				}		
						});
						
						
					});	
				</script>
		</div>
	</div>
	


</body>
</html>