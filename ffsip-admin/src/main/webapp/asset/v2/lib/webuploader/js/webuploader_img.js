define(['ff/webuploader'], function(WebUploader) {
	
	var func_upload_img = function(obj){
		
		if (typeof obj.list == 'undefined' || $(obj.list).length == 0) {
			
			alert('亲，请定义列表容器！');
			return false;
		}
		
		if (typeof obj.callback == 'undefined') {
			obj.callback = {};
		}
		
		var $list = $(obj.list),
			// 优化retina, 在retina下这个值是2
			ratio = window.devicePixelRatio || 1,

			// 缩略图大小
			thumbnailWidth = 100 * ratio,
			thumbnailHeight = 100 * ratio,

			// Web Uploader实例
			uploader;

		// 初始化Web Uploader
		uploader = WebUploader.create($.extend({}, OPT_WEBUPLOADER_IMG, obj || {}));

		// 当有文件添加进来的时候
		uploader.on( 'fileQueued', function( file ) {
			var $li = $(
					'<div id="' + file.id + '" class="file-item thumbnail">' +
						'<img>' +
						'<div class="info">' + file.name + '</div>' +
					'</div>'
					),
				$img = $li.find('img');

			$list.append( $li );

			// 创建缩略图
			uploader.makeThumb( file, function( error, src ) {
				if ( error ) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}

				$img.attr( 'src', src );
			}, thumbnailWidth, thumbnailHeight );
			
			if ($.isFunction(obj.callback.fileQueued)) {
				obj.callback.fileQueued(file);
			}
		});
		
		// 当一批文件添加进来的时候
		uploader.on( 'filesQueued', function( files ) {
			
			if ($.isFunction(obj.callback.filesQueued)) {
				obj.callback.filesQueued(files);
			}
		});

		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function( file, percentage ) {
			var $li = $( '#'+file.id ),
				$percent = $li.find('.progress span');

			// 避免重复创建
			if ( !$percent.length ) {
				$percent = $('<p class="progress"><span></span></p>')
						.appendTo( $li )
						.find('span');
			}

			$percent.css( 'width', percentage * 100 + '%' );
			
			if ($.isFunction(obj.callback.uploadProgress)) {
				obj.callback.uploadProgress(file, percentage);
			}
		});

		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on( 'uploadSuccess', function( file, response ) {
			$( '#'+file.id ).addClass('upload-state-done');
			
			if ($.isFunction(obj.callback.uploadSuccess)) {
				obj.callback.uploadSuccess(file, response);
			}
		});

		// 文件上传失败，现实上传出错。
		uploader.on( 'uploadError', function( file, reason ) {
			var $li = $( '#'+file.id ),
				$error = $li.find('div.error');

			// 避免重复创建
			if ( !$error.length ) {
				$error = $('<div class="error"></div>').appendTo( $li );
			}

			$error.text('上传失败');
			console.log(file.id + ': ' + reason);
			
			if ($.isFunction(obj.callback.uploadError)) {
				obj.callback.uploadError(file);
			}
		});

		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on( 'uploadComplete', function( file ) {
			$( '#'+file.id ).find('.progress').remove();
			
			if ($.isFunction(obj.callback.uploadComplete)) {
				obj.callback.uploadComplete(file);
			}
		});
		
		uploader.on('uploadFinished', function(){
			
			if ($.isFunction(obj.callback.uploadFinished)) {
				obj.callback.uploadFinished();
			}
		});
	};
	
	return func_upload_img;
});