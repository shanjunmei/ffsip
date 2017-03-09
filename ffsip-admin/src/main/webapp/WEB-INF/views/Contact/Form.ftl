<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>联系人编辑</title>
</head>
<body>
	
	<form action="${BasePath !}/Contact/save.do" method="post" id="myform" class="ff-form">
		<input type="hidden" name="id" value="${(entity.id) !}" />
		<div id="error_con" class="tips-form">
			<ul></ul>
		</div>


        <div class="form-group">
            <label><i>*</i>联系人编码：</label>
            <div>
                <input type="text"  name="code" value="${(entity.code) !}" data-rule-required="true" data-msg-required="联系人编码不能为空">
            </div>
        </div>

        <div class="form-group">
            <label><i>*</i>公司编码：</label>
            <div>
                <input type="text"  name="companyCode" value="${(entity.companyCode) !}" data-rule-required="true" data-msg-required="公司编码不能为空">
            </div>
        </div>

        <div class="form-group">
            <label>职位：</label>
            <div>
                <input type="text"  name="position" value="${(entity.position) !}" >
            </div>
        </div>

        <div class="form-group">
            <label>工号：</label>
            <div>
                <input type="text"  name="jobNumber" value="${(entity.jobNumber) !}" >
            </div>
        </div>


        <div class="form-group">
            <label>电话：</label>
            <div>
                <input type="tel"  name="phone" value="${(entity.phone) !}" >
            </div>
        </div>

        <div class="form-group">
            <label>email：</label>
            <div>
                <input type="email"  name="email" value="${(entity.email) !}" >
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

	<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/Contact/Contact_form.js?v=${ver !}"></script>
	<script type="text/javascript">

		$(function() {

			requirejs(['ff/validate'], function(){			
				executeValidateFrom('myform');
			});
		});

	</script>
</body>
</html>
