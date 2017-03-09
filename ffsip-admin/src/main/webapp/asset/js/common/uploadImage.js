var upload;
var uploadImages = {};


/**
 * 图片上传公共方法   --需初始化绑定浏览按钮
 * @param showImg		上传成功后  执行的js方法  用户自定义（必填）		
 * @param type			上传图片的类型====在基础数据系统-数据字典中设置-上传图片类型中添加（必填）		
 * @param button_id		上图图片的按钮（必填）	
 * @param file_size     图片大小,单位kb，默认50（选填）
 * @param path			追加自定义path（选填) 
 * 		最终URL：type/时间/path/name.jsp 			
 */
function initUploadImage(showImg,type,button_id,file_size,path){
	var IMAGE_SIZE_HUNDRED = "50";
	if(file_size != null && file_size!=''){
		IMAGE_SIZE_HUNDRED = file_size;
	}
	if(path != null && path!=''){
		path = "path:" + path;
	}else{
		path = "";
	}
	var upload = new SWFUpload({
			upload_url: rootPath + '/uploadImage/upload.do;jsessionid='+$('#jsessionid').val(),
			file_post_name:'image',
			post_params: {type:type,path},
			file_size_limit : IMAGE_SIZE_HUNDRED+"kb",
			file_types : "*.jpg;*.gif;*.png",
			file_types_description : "images/*",
			file_upload_limit : "0",
			file_queue_limit : "0",
			file_queued_handler: function(file){
//				$("#loading_firstPicName").css("display","inline");
				var f;
				f = $('<dd></dd>').appendTo($("#DL_"+type));
				$('<dt><img src="'+rootPath+'/asset/css/images/loading44.gif" /></dt>').appendTo(f);
			uploadImages[file.id] = f;
			},
			file_queue_error_handler : function(file, errorCode, message){
				try {
					if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
						$.frontEngineDialog.executeDialogContentTime('上传的文件太多啦');
						return;
					}
					switch (errorCode) {
						case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
							$.frontEngineDialog.executeDialogContentTime('<br>上传失败:<br><br>&nbsp;所选择的图片中，存在内存大小超出设定范围的图片');
							break;
						case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
							$.frontEngineDialog.executeDialogContentTime('上传的文件为空:'+file.name);
							break;
						case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
							$.frontEngineDialog.executeDialogContentTime('上传的文件类型不符:'+file.name);
							break;
						case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
							$.frontEngineDialog.executeDialogContentTime('上传的文件太多啦:'+file.name);
							break;
						default:
							$.frontEngineDialog.executeDialogContentTime('未知错误');
							break;
					}
				} catch (ex) {
			        this.debug(ex);
			    }
			},
			file_dialog_complete_handler : function(selectNum,queueNum){
				if(selectNum > 0 && queueNum > 0){
					upload.startUpload();
				}
			},
			upload_start_handler : function(){
				//上传开始
			},
			upload_progress_handler : function(file, bytesComplete, bytesTotal){
				//上传进程
				//var percent = getFormatPercent((bytesComplete/bytesTotal).toFixed(4)*100)+"%";
				//if(bytesComplete == bytesTotal){
				//}
			},
			upload_error_handler : function(file, errorCode, message){
				$.frontEngineDialog.executeDialogContentTime("图片上传报错，请重新上传！异常详细信息："+message);
			},
			upload_success_handler : function(file, serverData, responseReceived){
				//上传成功回调
				var serverDataJson = eval('(' + serverData + ')');
				console.log(serverDataJson);
				if(!serverDataJson){
					$.frontEngineDialog.executeDialogContentTime("图片上传报错，请重新上传！");
					return ;
				}
				if(serverDataJson.status==-1){
					$.frontEngineDialog.executeDialogContentTime("图片上传报错，请重新上传！");
					return ;
				}
				serverDataJson.buttonId = button_id;
				serverData = JSON.stringify(serverDataJson);
				showImg(serverData);
			},
			upload_complete_handler : null,
			button_image_url:rootPath+"/asset/css/images/liulan_btn.png",
			button_placeholder_id : button_id,
			button_width: 48,
			button_height: 22,
			button_cursor:SWFUpload.CURSOR.HAND,
			flash_url : rootPath+"/asset/js/control/SWFUpload/Flash/swfupload.swf",
			custom_settings : {
			},
			debug: false
		});
}