/**
 * ***************************************************************************
 * 前端页面js引擎
 * @创建日期 : 2015.12.29
 *      ***************************************************************************
 */
// TODO 格式还需进一步修改，采用strict方式。
(function($) {
	
	//取URL中的参数
	var getUrlParameter=function(name){
		var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");//正则表达式取得url中的参数
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]); return null;
	}
	
	
	//取URL中的参数
	var getImagPath=function(url){
		
		var _bannerUrl = url.replace(/_size/gm,'_origin');
		_bannerUrl = _pathTotal+'images/'+_bannerUrl;
		//_bannerUrl = 'http://192.168.1.44:8098/ffzx/images/'+_bannerUrl;
		
		return _bannerUrl;
		
	}
	
	//过滤html代码
	var removeHTMLTag = function(str) {
	    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
	    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
	    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
	    str=str.replace(/ /ig,'');//去掉 
	    return str;
	}

	


	// ****************************************************************************************************
	// $.frontEngine.methodName形式调用
	// ****************************************************************************************************
	$.extend({
		frontEngine: {
			getUrlParameter: function(name) {
				return getUrlParameter(name);
			},
            removeHTMLTag: function(str) {
                return removeHTMLTag(str);
            },
            getImagPath: function(url) {
                return getImagPath(url);
            }
            
		}
	});


}) (jQuery);