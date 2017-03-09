<!-- Main Header -->
<header class="col-sm-12 main-header">
	<!-- Logo -->
	<a href="javascript:void(0)" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>管理</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><img src="${BasePath !}/asset/img/logoIndex.png" alt="" /></span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> 
			<span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu hide">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<img src="${BasePath !}/asset/img/user2-160x160.jpg" class="user-image" alt="User Image"> 
						<span class="hidden-xs">Alexander Pierce</span>
					</a>
					<ul class="dropdown-menu">
						<li class="user-header">
							<img src="${BasePath !}/asset/img/user2-160x160.jpg" class="img-circle" alt="User Image">
							<p>admin</p>
						</li>
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">个人中心</a>
							</div>
							<div class="pull-right">
								<a href="${BasePath !}/logout.do" class="btn btn-default btn-flat">注销</a>
							</div>
						</li>
					</ul>
				</li>
				<li id="top_curr_warehouse" class="dropdown"><a><i class="fa fa-home"></i>当前作业仓：<span></span></a></li>
					<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" onclick="modifyPasswordWindow()"><i class="fa fa-sign-out"></i>修改密码</a>
				</li>
				<li class="dropdown user user-menu">
					<a href="${BasePath !}/logout.do" class="dropdown-toggle"><i class="fa fa-sign-out"></i>退出</a>
				</li>
			</ul>
		</div>
	</nav>
	<script>
var d ;
function modifyPasswordWindow() {
var curHost = window.location.host;
var indexValue = curHost.indexOf('ffzxnet');
var url = 'ffzxnet.com';
if(indexValue == '-1' || indexValue == -1){//本地或测试环境
  url = 'ffzx.com:8081';
}
var pmsStr = 'http://pms.'+url+'/pms-web/user/modifyPassword.do';
  if(d!=null){return false;}
	d = dialog({
    title: '修改密码',width: 380,height: 120,
    content: '<div class="col-lg-10 col-md-12 col-sm-12">  <div class="addForm1"><div class="form-tr"> <div class="form-td"> <label><i>*</i>原密码：</label> <div class="div-form"> <input class="form-control input-sm txt_mid" data-rule-required="true" data-msg-required="原始密码不能为空" type="password" placeholder="输入原始密码" id="srcPassword" name="srcPassword" > </div> </div> </div> <div class="form-tr"> <div class="form-td"> <label><i>*</i>新密码：</label> <div class="div-form"> <input class="form-control input-sm txt_mid" data-msg-required="新密码不能为空" type="password" placeholder="输入新密码" id="newPassword" name="newPassword" > </div> </div> </div> <div class="form-tr"> <div class="form-td"> <label><i>*</i>确认新密码：</label> <div class="div-form"> <input class="form-control input-sm txt_mid" data-msg-required="确认新密码不能为空" type="password" placeholder="输入确认密码" id="confirmPassword" name="confirmPassword" > </div> </div> </div></div></div>',
	okValue: '保存',
	onclose: function () {
        d = null;
    },
	ok: function(){
	      $('#tipId').remove();
	      var srcPassword = $('#srcPassword');
	      var newPassword = $('#newPassword');
	      var confirmPassword = $('#confirmPassword');
		  if(srcPassword.val() == ''){
		  	  $(srcPassword).after('<div id ="tipId" style="color:red;font-size:10px;margin-left:5px;">原密码不能为空</div>');
		  }else if(newPassword.val() ==''){
		      $(newPassword).after('<div id ="tipId" style="color:red;font-size:10px;margin-left:5px;">新密码不能为空</div>');
		  }else if(confirmPassword.val() ==''){
		      $(confirmPassword).after('<div id ="tipId" style="color:red;font-size:10px;margin-left:5px;">确认密码不能为空</div>');
		  }else if(newPassword.val() != confirmPassword.val() ){
		      $(confirmPassword).after('<div id ="tipId" style="color:red;font-size:10px;margin-left:5px;">新密码和确认密码不一置</div>');
		  }else{
	  	         $.ajax({ url : pmsStr,
                      data : {
                    	  srcPassword : srcPassword.val(),
                    	  newPassword : newPassword.val()
                      },// 给服务器的参数
                      type : "GET",
                      dataType : "jsonp",
                      async : false,
                      jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
                      jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
                      error: function(xhr){
                                alert("请求出错(请检查相关度网络状况.)");
                             },
                      success : function(result) {
                          if (result.code == '0' || result.code == 0) {
                             d.close();
                             var dx = dialog({ content: '密码修改成功' }); dx.show(); setTimeout(function () {dx.close().remove(); window.location.href='${BasePath !}/logout.do';}, 1500)
	                         d = null;
	                         return true;
                          } else {
                             var dy = dialog({ content: result.msg }); dy.show(); setTimeout(function () {dy.close().remove(); }, 1500)
                          }
                      }
                  });

		    }
		    return false;
		}
});
d.showModal();
}
</script>
</header>