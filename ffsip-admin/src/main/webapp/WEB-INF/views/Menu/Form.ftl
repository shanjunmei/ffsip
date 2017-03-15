<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>菜单编辑</title>
</head>
<body>
	
	<form action="${BasePath !}/Menu/save.do" method="post" id="myform" class="ff-form">
		<input type="hidden" name="id" value="${(entity.id) !}" />
		<div id="error_con" class="tips-form">
			<ul></ul>
		</div>

		<div class="form-group">
			<label><i>*</i>菜单名称：</label>
			<div>
				<input type="text"  name="name" value="${(entity.name) !}" data-rule-required="true" data-msg-required="菜单名称不能为空">
			</div>
		</div>
        <div class="form-group">
            <label><i>*</i>菜单编码：</label>
            <div>
                <input type="text"  name="code" value="${(entity.code) !}" readonly="readonly">
            </div>
        </div>



        <div class="form-group">
            <label><i>*</i>菜单URL：</label>
            <div>
                <input type="text"  name="href" value="${(entity.href) !}" >
            </div>
        </div>


        <div class="form-group">
            <label><i>*</i>菜单URL(全)：</label>
            <div>
                <input type="text"  name="url" value="${(entity.url) !}" >
            </div>
        </div>

        <div class="form-group">
            <label><i>*</i>排序：</label>
            <div>
                <input type="text"  name="sort" value="${(entity.sort) !}" >
            </div>
        </div>

		<div class="form-group">
			<label>菜单备注：</label>
			<div>
				<input type="text"  name="remarks" value="${(entity.remarks) !}">
			</div>
		</div>


		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="保存">

			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</div>	

	</form>

	<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/Menu/Menu_form.js?v=${ver !}"></script>
	<script type="text/javascript">

		$(function() {
		
			requirejs(['ff/select2'], function(){
				$("select").select2();
			});
			
			requirejs(['ff/validate'], function(){			
				executeValidateFrom('myform');
			});
		});

	</script>
</body>
</html>
