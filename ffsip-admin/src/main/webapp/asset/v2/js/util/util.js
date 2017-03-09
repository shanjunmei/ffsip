var alertInited = function(e){
	var $alert = $(e).next('.alert');
	$alert.fadeIn(function(){
		setTimeout(function(){ $alert.fadeOut() }, 1500)
	});
};

var funcLoadSource = function(){
	
	var $wrapperComponent = $(this).closest('.wrapper-component');
	var $js = $wrapperComponent.find('pre');
	
	if ($js.length != 0) return false;
	
	var sourceWrapper = '<textarea class="form-control copy-html" style="display:none;"></textarea><pre class="prettyprint" style="display:none;"></pre>';
	var sourceBtn = '<span class="alert alert-success pull-left" role="alert"><strong>Well done! It is working now :)</strong></span><a href="#" class="btn btn-default pull-right">UP</a>';
	
	$wrapperComponent.find('.wrapper-btn').after(sourceWrapper);
	$wrapperComponent.find('.btn-init').after(sourceBtn);
	
	var	html = $(this).data('html') || '';
	if (html != '') $wrapperComponent.find('.copy-html').val(html).show();
	
	var js = $.trim($wrapperComponent.find('>script').html());			
	if (js != '') {
		$wrapperComponent.find('pre').html(js).show();
		PR.prettyPrint();
	}
	
	alertInited(this);			
};

$(document).ready(function(){
	
	$('.btn-init').each(function(){
		var $btn = $(this);
		var $wrapperComponent = $btn.closest('.wrapper-component');
		var html = $.trim($wrapperComponent.find('.wrapper-html').html());
		
		$btn.data('html', html);
	});
	
	$(document).on('click', '.btn-init', funcLoadSource);
});