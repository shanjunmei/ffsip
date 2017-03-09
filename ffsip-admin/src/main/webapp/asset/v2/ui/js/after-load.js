window.onload = function(){
		
	if (top.location !== self.location){
		var wiObj = window.name;
		var curIframeObj = wiObj.split(",");
		var curIframeID = curIframeObj[0];
		var getCurUrl = window.location.host;
		
		if (typeof curIframeObj[1] != 'undefined') {
		
			if (curIframeObj[1].indexOf(getCurUrl) == -1) {
				parent.postMessage(curIframeID+',show', curIframeObj[1]);
			} else {
			
				if($('.currighttag', parent.document).length){
					$('.currighttag', parent.document).css("display","none");
				}
				
				if($('.md-loading', parent.document).length){
					$('.md-loading', parent.document).css("display","none");
				}
				$('iframe[id="' + curIframeID + '"]', parent.document).css("visibility","visible");
			}
		}		
	}
	
	if ($('.currighttag').length) {
		$('.currighttag').css("display","none");
	}	
}