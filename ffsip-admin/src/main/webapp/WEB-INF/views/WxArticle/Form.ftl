<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>微信文章编辑</title>
</head>
<body>
	
	<form action="${BasePath !}/WxArticle/save.do" method="post" id="myform" class="ff-form">
		<input type="hidden" name="id" value="${(entity.id) !}" />
		<div id="error_con" class="tips-form">
			<ul></ul>
		</div>

		<div class="form-group">
			<label><i>*</i>标题：</label>
			<div>
				<input type="text"  name="title" value="${(entity.title) !}" data-rule-required="true" data-msg-required="标题不能为空">
			</div>
		</div>

        <div class="form-group">
            <label>排序：</label>
            <div>
                <input type="text"  name="indexSort" value="${(entity.indexSort) !}">
            </div>
        </div>

		<div class="form-group">
			<label>备注：</label>
			<div>
				<input type="text"  name="remarks" value="${(entity.remarks) !}">
			</div>
		</div>


		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="保存">

			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</div>	

	</form>

	<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/WxArticle/WxArticle_form.js?v=${ver !}"></script>
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
