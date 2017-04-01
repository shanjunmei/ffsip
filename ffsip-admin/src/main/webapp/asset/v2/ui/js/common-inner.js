var ffzx = ffzx || {};

if (typeof debounce != 'function') {
	
	var debounce = function(func, wait, immediate) {
	    var timeout;
	    return function() {
	        var context = this, args = arguments;
	        var later = function() {
	            timeout = null;
	            if (!immediate) func.apply(context, args);
	        };
	        var callNow = immediate && !timeout;
	        clearTimeout(timeout);
	        timeout = setTimeout(later, wait);
	        if (callNow) func.apply(context, args);
	    };
	}
}

ffzx.map = {
	ui: {
		datatable: 'ff/init_datatable',
		dialog: 'frontEngineDialog',
		ztree: 'ff/ztree',
		treetable: 'ff/treetable',
		validate: 'ff/validate',
		select2: 'ff/select2',
		multiselect: 'bs/multiselect',
		datepicker: 'jq/datetimepicker',
		upload: 'ff/webuploader',
		ueditor: 'ff/ueditor'
	}
}

ffzx.ui = function(deps, func){
	
	if ($.type(deps) == 'array' && $.type(func) == 'function') {
		
		var _deps = $.map(deps, function(ele){
			return (ele in ffzx.map.ui) ? ffzx.map.ui[ele] : ele;
		});
		
		requirejs(_deps, function(){				
			func();
		});
		
	} else {
		console.log('ffzx.ui(array, function) 参数类型错误');
	}
}

ffzx.opt = {
	ueditor: {
		initialFrameWidth : '100%',
		initialFrameHeight : 360,
		wordCount : true,
		maximumWords : 300 
	}
};
ffzx.chart = {};
ffzx.ueditor = {};
ffzx.upload = {};

ffzx.init = {
	chart: function(domId, opt){
		ffzx.chart[domId] = echarts.init(document.getElementById(domId));
		ffzx.chart[domId].resize();
		ffzx.chart[domId].setOption(opt);
	},
	ueditor: function(){
		
		var $ueditor = $('.ueditor');
		
		if ($ueditor.length == 0) return false;
		
		requirejs(['ff/ueditor'], function(){
			
			//setTimeout(function(){
				$ueditor.each(function(){
					
					if ('id' in this) {
						var id = this.id;
						
						if ($.type(UE.getEditor) == 'function') {
							ffzx.ueditor[id] = UE.getEditor(id, ffzx.opt.ueditor);
						}
					}
				});
			//}, 500);
		});
	},
	dateRange: function(obj){
		var $from = null;
		var $to = null;
		var _opt = {
          	//defaultDate: "+1w",
          	//numberOfMonths: 2
        };
		var _method = ('showTime' in obj && obj.showTime == true) ? 'datetimepicker' : 'datepicker';
		var opt = $.extend({}, OPT_DATEPICKER, _opt, obj);
		var getDate = function(element){
	        var date;
	        
	        try {
	      	  date = $.datepicker.parseDate(opt.dateFormat, element.value);
	        } catch( error ) {
	      	  date = null;
	        }	   
	        return date;
	    };
		
		$from = $('#' + obj.id_from)[_method]($.extend({}, {
      		
      		onSelect: function(){
      			!!$to && $to.datepicker( "option", "minDate", $from.val());
      		}
      	}, opt));
        
      	$to = $('#' + obj.id_to)[_method]($.extend({}, {
      		
      		onSelect: function(){
      			!!$from && $from.datepicker( "option", "maxDate", $to.val());
      		}
      	}, opt));
      	
      	$from.add($to).attr('readonly', true);
	},
	dateInput: function(obj){
		var _opt = {
          	//defaultDate: "+1w",
          	//numberOfMonths: 2
			timeFormat: 'HH:mm:ss'
        };
		var _method = ('showTime' in obj && obj.showTime == true) ? 'datetimepicker' : 'datepicker';
		var opt = $.extend({}, OPT_DATEPICKER, _opt, obj);
		var $input = $('#' + obj.id_input).attr('readonly', true);
		
		if ('showCalendar' in obj && obj.showCalendar == false) {
			_method = 'timepicker';
		}
		$input[_method](opt);
	}	
};

ffzx.util = {
	randomNum: function() {
		return Math.round(Math.random()*1000);
	},
	
	onWinResize: function(){
		
		// 重设图表大小
		if ($.isEmptyObject(ffzx.chart) == false) {
			
			$.each(ffzx.chart, function(){
				this.resize();
			});
		}
		ffzx.util.adjustInput();
		ffzx.util.adjustDatatable();		
	},
	
	adjustInput: function(){
		
		// re-render :input
		$('.form-inline .form-group').each(function(idx, ele){
			var cssBlock = 'col-lg-2 col-sm-3';
			var offset = 8;
			
			if ($(this).hasClass('col-span-2')) {
				cssBlock = 'col-lg-4 col-sm-6';
			}
			
			if ($(this).hasClass('col-span-3')) {
				cssBlock = 'col-lg-6 col-md-6 col-sm-6';
			}
			
			var $block = $(this).addClass(cssBlock);
			
			if ($block.hasClass('br') && $block.next().is('.clearfix') == false) {
				$block.after('<div class="clearfix"></div>');
			}
			
			if ($block.hasClass('single-row')) {
				offset = 20;
			}
		
			var wBlock = $block.width();
			var wLabel = $block.find('>label').outerWidth();
			var $input = $block.find(':input').not(':checkbox, :radio, :button, input[type=hidden]').addClass('form-control');
			var isSelect = $input.is('select');
			var $select2 = $block.find('.select2');
			var isDblInput = $input.length == 2;
			
			$block.find('>div').width(wBlock - wLabel - offset).addClass('pull-right');
			
			if ($input.is(':text') || $input.is('textarea') || isSelect) {
				var _w = wBlock - wLabel - offset;
				$input.add($select2).css({'display': 'inline-block', 'width':'100%'});
				
				if (isDblInput) {
					$block.find('>div').addClass('text-center');
					$input.width((_w - 10)/2);
					$input.first().addClass('pull-left').attr('placeholder', '从');
					$input.last().addClass('pull-right').attr('placeholder', '到');
				}
				
				if ($input.is('textarea')) {
					$input.attr('placeholder', '可填写多行');
				}
			}
			
			/*
			if ($block.find('.ueditor, textarea').length > 0) {
				$block.add($block.find('>div')).css({'width':'100%'});
				$block.find('>div').css({'padding-right':0});		
			}
			*/
		});
	},
	
	adjustDatatable: function(){
		// 修正 datatable 的列宽
		$.each(FFZX.DT, function(key, val){
			
			if ('columns' in val) {
				val.columns.adjust();
			}
		});
	},
	
	qrcode: function(_opt){
		var opt = _opt;
		
		if ($.type(opt.id) == 'undefined') {
			alert('请定义二维码容器的  id');
			return false;
		}
		
		var width = (isNaN(opt.width)) ? 96 : opt.width;
		var height = (isNaN(opt.height)) ? 96 : opt.height;
		var content = ($.type(opt.content) == 'undefined') ? window.location.href : opt.content;
		var colorDark = ($.type(opt.colorDark) == 'undefined') ? "#000000" : opt.colorDark;
		var colorLight = ($.type(opt.colorLight) == 'undefined') ? "#ffffff" : opt.colorLight;
		
		requirejs(['ff/qrcode'], function(){
		    var qrcode = new QRCode(document.getElementById(opt.id), {
		        width: width,
		        height: height,
		        colorDark: colorDark,
				colorLight: colorLight
		    });
		    qrcode.makeCode(content);
		});
	}
};

$(window).resize(debounce(ffzx.util.onWinResize, 200));
$(window).load(ffzx.util.adjustInput);

//方法：生成整页的 iframe 浮动窗口
function iframeFullPage(url, winSize){

	var _winSize = {};	
	
	$('body').addClass('noScroll');
	$('[id="mask_loading"]').show();
	
	var $iframe = $('.iframeFullPage');
	var winHeight = $(self.window).height();
	var winWidth = '100%';
	var winName = 'ifp' + url.split('?')[0].replace(/[\W]/ig, '_');
	
	if ($iframe.length) {
		$iframe.remove();
	}

	if (typeof winSize != 'undefined') {		
	
		if ($.type(winSize) == 'object') {
			_winSize = winSize;
			
		} else {
			var _objSize = {
					
				lg: {w: '600', h: '450'},
				md: {w: '450', h: '300'},
				sm: {w: '360', h: '240'}
			}
			
			if (winSize in _objSize) {
				_winSize = _objSize[winSize];
			} else {
				_winSize = _objSize.lg;
			}
		}
		
		if ('w' in _winSize && 'h' in _winSize) {
			winHeight = _winSize.h;
			winWidth = _winSize.w;
		}
		
		winWidth = winWidth + 'px';
		
	}
	
	var iframe = '<iframe frameborder="0" scrolling="auto" src="'+ url +'" name="' + winName + ','+ window.location.href + '" class="iframeFullPage" style="height:' + winHeight + 'px;width:' + winWidth + '">';
	$('body').append(iframe);
	
	window.addEventListener("message", function( event ) {
	
		// 监听从 iframe 中 post 过来的数据
		var data = $.trim(event.data);
		
		if (data.indexOf('{') == 0) {
			var json = $.parseJSON(data);
			var _callback = $.trim(json.cmd);
			var _data = ('data' in json) ? $.trim(json.data) : '';

			if (_callback in window && $.isFunction(window[_callback])) {
				
				if (_data != '') {
					
					window[_callback](_data);
				} else {
					
					window[_callback]();	
				}							
			}
		}
		
	}, false );	
}

function ztreeInit(zNodes, nodeId){
	var nodeId = (typeof nodeId == 'undefined') ? 'left_menu_tree' : nodeId;
	var tree_setting = {
		data : {
			simpleData : {
				enable : true
			},
			key:{
				url:""
			}
		},
		view:{
			selectedMulti: false
		},
		callback : {
			beforeClick : beforeClick
		}
	};
	var instanceZtree = $.fn.zTree.init($('#' + nodeId), tree_setting, zNodes);
	//$.fn.zTree.getZTreeObj(nodeId).expandAll(false);	
}


$(document).ready(function(){
	
	$('table').each(function(){		
		var $tbl = $(this);
		
		if ($tbl.hasClass('ff-tbl')) {
			$tbl.addClass('table table-hover table-striped table-bordered dataTable');
		}
	});
	
	$('.ff-btn').each(function(){		
		var $btn = $(this);
		var css = 'btn btn-primary';
		
		if ($btn.hasClass('sm')) {
			css += ' btn-sm';
		}
		
		if ($btn.hasClass('white')) {
			css = css.replace('btn-primary', 'btn-default');
		}
		
		$btn.addClass(css);		
	});
	
	$('.ff-form').each(function(){
		var $form = $(this);
		
		$form.addClass('row form-inline');
	});
	
	ffzx.init.ueditor();
	
	//左侧树状菜单区块开关
	$(".openClose").click(function (){
		var cssClass = 'treeOpened';
		var $body = $('body');
		var isTreeOpen = $body.hasClass(cssClass);
		
		if (isTreeOpen) {
			$body.removeClass(cssClass);
		} else {
			$body.addClass(cssClass);
		}
	});
});