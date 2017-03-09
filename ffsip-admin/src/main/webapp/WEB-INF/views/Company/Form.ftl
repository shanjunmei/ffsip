<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>公司信息编辑</title>

</head>
<body>
	
	<form action="${BasePath !}/Company/save.do" method="post" id="myform" class="ff-form">
		<input type="hidden" name="id" value="${(entity.id) !}" />
		<div id="error_con" class="tips-form">
			<ul></ul>
		</div>
		<style type="text/css">

			.tab td i{color:#ff0000; margin-right:0.5em;}
			.tab td{padding:0 10px 15px 0;}
			.form-group.tab.col-lg-2.col-sm-3{width:100%;}
		    
		</style>
		<table border="0" cellpadding="0" cellspacing="0" class="form-group tab">
		 <tr>
	       <td valign="top" align="right" width="140"><i>*</i>名称：</td>
	       <td valign="top" align="left"><input type="text"  name="name" value="${(entity.name) !}" data-rule-required="true" data-msg-required="名称不能为空"></td>
     	</tr>

            <tr>
                <td valign="top" align="right" width="140"><i>*</i>简称：</td>
                <td valign="top" align="left"><input type="text"  name="abbreviation" value="${(entity.abbreviation) !}" data-rule-required="true" data-msg-required="简称不能为空"></td>
            </tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>绑定会员编码：</td>
	       <td valign="top" align="left"><input type="text"  name="memberCode" value="${(entity.memberCode) !}"
	       				data-rule-required="true" data-msg-required="绑定会员编码不能为空"></td>
     	</tr>
     	

     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>备注：</td>
	       <td valign="top" align="left"><input type="text"  name="remarks" value="${(entity.remarks) !}"></td>
     	</tr>
     	

       	 
       	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>公众号二维码图片：</td>
	       <td valign="top" align="left">
	       		<input type="hidden" id="wxImg" name="wxImg" value="${(entity.wxImg) !}" >
	       		<div class="form-group single-row">
						<!--  <#if (entity.image)??><img alt="" src="${(entity.image) !}"></#if> -->
					    <div class="webuploader" id="upload_wxImg"></div>
				</div>
	       </td>
     	</tr>

            <tr>
                <td valign="top" align="right" width="100"><i>*</i>logo图片：</td>
                <td valign="top" align="left">
                    <input type="hidden" id="logoImg" name="logoImg" value="${(entity.logoImg) !}" >
                    <div class="form-group single-row">
                        <!--  <#if (entity.logoImg)??><img alt="" src="${(entity.logoImg) !}"></#if> -->
                        <div class="webuploader" id="upload_logoImg"></div>
                    </div>
                </td>
            </tr>

            <tr>
                <td valign="top" align="right" width="100"><i>*</i>介绍：</td>
                <td valign="top" align="left">
                    <textarea rows="5" cols="50" name="introduction" >${(entity.introduction) !}</textarea>
                </td>
            </tr>
           

        </table> 
		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="保存">

			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</div>	

	</form>
	<script type="text/javascript">

		$(function() {
		
			requirejs(['ff/select2'], function(){
				$("select").select2();
			});
			
			requirejs(['ff/validate'], function(){			
				executeValidateFrom('myform');
			});
			
			ffzx.ui(['datepicker'], function(){
			     
			    //日期区间，两个 input   
			    /* ffzx.init.dateRange({
			        id_from: 'beginLastUpdateDateStr', //input id
			        id_to: 'endLastUpdateDateStr', //input id
			        showTime: true //显示小时分秒
			    }); */
			     
			   /* //单个日期 input
			    ffzx.init.dateInput({
			        id_input: 'startDateTimeStr' //input id
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'endDateTimeStr' //input id
			    });*/
			});
			
			ffzx.ui(['upload'], function(){

				//微信二维码图片上传
                var wxImg=$("#wxImg").val();

			    ffzx.init.upload({
			        id: 'upload_wxImg',
			        type: 'image', // type: image, file
			        multiple: false, // false: 限制只上传一个图片/文件; 默认为 true: 可上传多个
			        server: rootPath+'/FileRepo/upload.do', // Backend script receiving the file(s)
			        
			        
			        <#if entity.wxImg ??>
			          uploaded:[
				   				{
				   					id: '',
				   					name: '',
				   					src: wxImg
				   				}
				   		],
			    	</#if>
			        	
			         
			        // 以下为可选
			        callback: {
			             

			             
			            // 'response' is returned from server
			            uploadSuccess: function(file, response){ 
			            	var path=rootPath+"/FileRepo/file.do?id="+response.id; 
			           		$("#wxImg").val(path);
			                console.log(arguments);
			            },

			        }
			    });


                //LOGO图片上传
                var logoImg=$("#logoImg").val();

                ffzx.init.upload({
                    id: 'upload_logoImg',
                    type: 'image', // type: image, file
                    multiple: false, // false: 限制只上传一个图片/文件; 默认为 true: 可上传多个
                    server: rootPath+'/FileRepo/upload.do', // Backend script receiving the file(s)


				<#if entity.logoImg ??>
                    uploaded:[
                        {
                            id: '',
                            name: '',
                            src: logoImg
                        }
                    ],
				</#if>


                    // 以下为可选
                    callback: {



                        // 'response' is returned from server
                        uploadSuccess: function(file, response){
                            var path=rootPath+"/FileRepo/file.do?id="+response.id;
                            $("#logoImg").val(path);
                            console.log(arguments);
                        },

                    }
                });


            });
		});
		


	</script>
</body>
</html>
