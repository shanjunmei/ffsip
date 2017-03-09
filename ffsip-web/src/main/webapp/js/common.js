//var _pathTotal = "../data/getNavgation.json";
var _pathTotal = location.protocol +'//'+ location.host + '/ffsip-web/api/';
var _commonbasePath = location.protocol +'//'+ location.host + '';
//var _pathTotal = 'http://192.168.2.182:9999/ffzx/';


//每页banner
function pageTabBanner(number,container){
	var _basePath = _pathTotal + 'data/'+number;
	//var _basePath = _pathTotal + "ffzx_site/trunk/src/main/webapp/v3/data/indexBanner.json";
	var _banner = null;
	var _bannerJson = {};
	//$.frontEngineAjax.executeAjax(_basePath,"get",_bannerJson,function(bannerRsult){
	common_ajax({url:_basePath,success:function (bannerRsult) {	
		var _bannerHtml = '';
		for(var i = 0;i<bannerRsult.length;i++){
			
			var _bannerUrl = $.frontEngine.getImagPath(bannerRsult[i].imageUrl);
			
			_bannerHtml = _bannerHtml +'<li><div class="bannerBg" style="background-image: url('+_bannerUrl+')"><div class="banner11"><img src="'+_bannerUrl+'" ></div></div></li>';
		}
	
		$(container).html(_bannerHtml);
		banner();
	}});	
}


	var common_ajax = function(options){
		var that = this;
	
			//可选参数
			that.options = {
				type : options.type || "post",	
				async: options.async || "true",  
				data : options.data || {"date": new Date().getTime()},
				url: options.url || "",
				dataType : options.dataType || "json",
				success: options.success || null
			}
			
		 $.ajax({
            type: that.options.type,
            async: that.options.async,
            data: that.options.data,
            url: that.options.url,
            dataType: that.options.dataType,
            success: function(d){
				//$.alert(d);
                if(d.constructor === Object){

                	if(d.code == -1){
                		location.href ='Signin.html';
						return;
                	}else if(d.code==-2){
						location.href=d.url;
						return;
                	}else if(d&&d.errorCode){
						$.alert(d.msg);
						return;
					}
                }
				that.options.success(d);
            },
            error: function(e){               
				
				// 如果超时退出
				/*if (e.readyState == 0 && e.responseText == '' && e.status == 0 && e.statusText == 'error') {
					$.frontEngineDialog.executeDialogContentTime('请重新登录！', 5000);
				} else {
					errorfn(e);
				}*/
				console.log(e);
            }
        });

		
	}


//Date - format
if (!Date.prototype.format) {
    Date.prototype.format = function(fmt) {
        var o = {
            'M+': this.getMonth() + 1,
            'd+': this.getDate(),
            'h+': this.getHours(),
            'm+': this.getMinutes(),
            's+': this.getSeconds(),
            'q+': Math.floor((this.getMonth() + 3) / 3),
            'S': this.getMilliseconds()
        }

        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));

        for (var k in o)
            if (new RegExp('(' + k + ')').test(fmt))

                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));

        return fmt;
    }
}