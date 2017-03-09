if (typeof FFZX == 'undefined') {
	
	var FFZX = {
		// Namespace for DataTables
		DT: {			
			get: function(id) {
				
				var dtInstance = FFZX.DT['dt_' + id];
				
				if (typeof dtInstance != 'undefined') {
					return dtInstance;
				}
			}
		}	
	};
	var resizeTimer = null;
	var getCurIframeName = window.name || '';
	var getCurUrl = window.location.host;
	var pathname = window.location.pathname;

	if (typeof rootPath == 'undefined') {
		var rootPath = '/' + pathname.split('/')[1];
	}

	var curIframeObj = getCurIframeName.split(",");
	var curIframeID = curIframeObj[0] || '';
	var top_href = curIframeObj[1] || '';
	var isDeeperIframe = parent.window != top.window;
	var deeperIframeId = (isDeeperIframe && getCurIframeName != '') ? parent.$('iframe[name="' + getCurIframeName + '"]').attr('id') || '' : '';
	//var cookie_page_prev = 'page_prev' + ((deeperIframeId != '') ? '_' + deeperIframeId : '');
	var cookie_page_prev = 'page_prev_' + curIframeID;

	var path4Cookie = window.location.pathname.replace(/[\W]/ig, '_');
	var pageHistoryPrev = null;

	var righttag = '<div class="currighttag md-loading md-loading-global"></div>';

	FFZX.openPageTab = function(tabID, param){
		
		var arrParam = [];
		
		if (typeof param != 'undefined') {
			
			$.each(param, function(key, val){
				
				arrParam.push(key + '=' + val);
			});
		}

		top.postMessage(curIframeID + ',pageTab:#' + tabID + ':' + arrParam.join('&'), curIframeObj[1]);
	}

	$(function(){

		
		//方法舍弃：做预留
		/*if($("#messageBox").length>0){
			var messageBoxTxt=$("#messageBox").attr("message");
			$.frontEngineDialog.executeDialogContentTime(messageBoxTxt,2000);
		}
		*/
		
		var curIframes=document.getElementsByTagName("iframe");
			
			if(curIframes.length > 0){
				for(var i=0;curIframes.length>i;i++){
					var odlname=curIframes[i].name;
					
					if((odlname==null || odlname=="" || typeof(odlname)=="undefined")){
						var crtif=curIframes[i].id;
						odlname = (crtif==null || crtif=="" || typeof(crtif)=="undefined")? new Date().getTime() : crtif;
						
						curIframes[i].id = odlname;
					}
					curIframes[i].name=odlname+','+window.location.href;
					curIframes[i].contentWindow.name = odlname +','+window.location.href;
				}
			}else{
			}


		// 全选/反选
		$(".table tr th input[type='checkbox']").click("click", function() { 
			var evt = arguments[0] || window.event;
			var chkbox = evt.srcElement || evt.target;
			var checkboxes = $(".table tr td input[type='checkbox']");
			if (chkbox.checked) {
				checkboxes.prop('checked', true);
			} else {
				checkboxes.prop('checked', false);
			}
		
		});
		
		// 全选/反选 v2
		$('.ff_DataTable').on('click', 'th :checkbox', function() {
			var $table = $(this).closest('.dataTables_wrapper');
			var checkboxes = $table.find('td :checkbox');
			checkboxes.prop('checked', this.checked);
		});
		
		//批量删除按钮
		$("#table-deleteBtn").click("click", function() {
			delAccount();
		});
		
		$('.btn_delete_batch').unbind().click(function(e){
			e.preventDefault();
			var dataTableId = $(this).data('target');

			delAccount('', function(){
				reloadData(dataTableId);
			});
		});
		
		//delete_item  ：删除单个
		$(".table tr td a[name='delete_item']").click("click", function() { 
			var url =this.href;
			var $this = $(this);
			$.frontEngineDialog.executeDialog('delete_table_info','信息','<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　删除之后该信息会被彻底删除,是否继续？　　','100%','100%',
					function(){
						
						if ($this.hasClass('action-delete-item')) {
							
							common_doSave(url,"get", '', '', '', function(){
								$this.closest('tr').remove();
							});
						} else {
							
							common_doSave(url,"get");
						}
					}
				);
			return false;
		});

		//enabled_item  ：禁用单个
		$(".table tr td a[name='enabled_item']").click("click", function() { 
			var url = this.href;
			$.frontEngineDialog.executeDialog('delete_table_info','信息','<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　是否' + $(this).text() + '!','200','50',
					function(){
						//window.location.href=url;
						common_doSave(url,"get");
					}
				);
			return false;
		});
		
		
		$(document).on('click', '.ztree li', function(e){
			e.stopPropagation();
			var ztreeId = $(this).closest('.ztree').attr('id');
			var selectedNodeId = this.id;
			
			FFZX.setCookie(path4Cookie + '_' + ztreeId, selectedNodeId, 1/(24*12));
		})		
		.on('mouseenter', '[onclick^=export]', function(){		
			window.onbeforeunload = null;
		})
		.on('mouseleave', '[onclick^=export]', function(){		
			window.onbeforeunload = FFZX.funcBeforeUnload;
		})
		.on('mouseenter', 'input[value="返回"], .btn-go-back', function(){
			var strOnclick = $(this).attr('onclick') || '';
			
			if ($.trim(strOnclick) != '') {
				$(this).data('strOnclick', strOnclick);
				$(this).removeAttr('onclick');
			}
		})
		.on('click', 'input[value="返回"], .btn-go-back', function(){	
			var pageHistoryPrev = FFZX.getCookie(cookie_page_prev);
			var strOnclick = $(this).data('strOnclick');
			var regx = new RegExp("\\((.| )*?\\)","igm");
			
			// 提取原来 isReturn(param) 中的 param（不含前后的单/双引号）
			if (/isReturn/.test(strOnclick)) {
				var str = strOnclick.match(regx)[0];
				
				if (str != '()') {
					pageHistoryPrev = str.substring(2, str.length - 2);
				}
			}
			
			if (/location/.test(strOnclick)) {
				var href = $.trim(strOnclick.split('location.href')[1].split(';')[0]);
				pageHistoryPrev = $.trim(href.substring(1));
				pageHistoryPrev = pageHistoryPrev.substring(1, pageHistoryPrev.length - 1);
				pageHistoryPrev = $.trim(pageHistoryPrev);
			}
			
			// 判断各种奇葩的 onclick 事件
			if (/onBack/.test(strOnclick)) {			
				if ($.isFunction(onBack)) { onBack(); }
				
			} else if (/back/.test(strOnclick)) {			
				if ($.isFunction(back)) { back(); }
				
			} else if (/onClose/.test(strOnclick)) {			
				if ($.isFunction(onClose)) { onClose(); }
				
			} else if (/parent.location/.test(strOnclick)) {
				parent.location.href = pageHistoryPrev;
				
			} else if (/location/.test(strOnclick)) {
				location.href = pageHistoryPrev;
				
			} else if (/history/.test(strOnclick)) {
				location.href = pageHistoryPrev;
					
			} else if (pageHistoryPrev != null) {
			
				// 重新调用 isReturn 方法	
				isReturn(pageHistoryPrev);
			}
		})
		.on('click', '.btn-close-iframeFullPage', function(){
			
			postCmd({"cmd":"closeIframeFullPage"});
		});
		
		// Init loading mask
		$('body').append('<div id="mask_loading"></div>');
		$.ajax({
			url: rootPath + '/asset/js/control/mask/canvasloader-min.js',
			dataType: 'script',
			cache: true,
			success: function(){
				var cl = new CanvasLoader('mask_loading');
				cl.setColor('#cccccc');
				cl.setShape('spiral');
				cl.show();
			}
		});
		
		$('select').each(function(){
				
			var $this = $(this);
			var strOption = $this.attr('data-option');			
			var arrOption = [];
			var strSelected = $this.attr('data-selected');
			var arrSelected = (typeof strSelected != 'undefined') ? strSelected.split(',') : [];
			var strPlaceholder = $this.attr('data-hint');
			strPlaceholder = (typeof strPlaceholder != 'undefined') ? '<option value="">'+ strPlaceholder +'</option>' : '';

			if (typeof strOption != 'undefined') {
				
				if (arrSelected.length > 1) {
					$this.attr('multiple', true);
				}
				
				$.each($.parseJSON(strOption), function(idx, ele){
					var selected = '';
					
					if ($.inArray(ele.value, arrSelected) > -1) {
						selected = ' selected="selected"';
					}
					arrOption.push('<option value="'+ ele.value +'" '+ selected +'>'+ ele.label +'</option>');
				});
				
				$this.append(strPlaceholder + arrOption.join(''));
				$this.width($this.outerWidth() + 20);

				if ($this.attr('multiple') == 'multiple') {

					requirejs(['bs/multiselect'], function() {
						$this.multiselect();
					});
					
				} else {
					
					requirejs(['ff/select2'], function() {
						$this.select2({
							//allowClear: true,
							//maximumSelectionLength: 1,
						});
					});					
				}
			}			
		});
		
		$('.btn-clear-keyword').on('click', function(e){
			e.preventDefault();
			
			var $this = $(this);
			var dataTableID = 'dt_' + $this.data('target');
			var formID = $this.closest('form').attr('id');
			
			FFZX.resetSearch(formID, true, dataTableID);
		});	
		
		//回车键触发表单提交
		$('form').on('keydown', function(e){
			var	$btnSubmit = $(this).find('#find-page-orderby-button');
			
			if ($btnSubmit.length && e.which == 13) {
				e.preventDefault();	
				$btnSubmit.click();
				return false;
			}
		});
		
		// 窗口变化大小时，触发最后一次
		$(window).on('resize', function(){
			var $iframeFullPage = $('.iframeFullPage');
			var winHeight = $(self.window).height();
			
			if ($iframeFullPage.length) {
				
				var doResize = function(){				
					$iframeFullPage.height(winHeight);
				}
			
				if (resizeTimer) {
					clearTimeout(resizeTimer);
				}		
				resizeTimer = setTimeout(doResize, 0);  
			}		
		});
		
		// 点击打开页签“仓储系统-系统设置-选择作业仓库”
		$(document).on('click', '#top_curr_warehouse', function(){	
			loadPageTab('#a49e2d167df046aebe1ba06a5b734cd6');
		});
	});
	// END: document ready
	
	FFZX.resetSearch = function(formID, isReload, dataTableID){			
		var $form = (typeof formID != 'undefined' && formID != '' && formID != null) ? $('#' + formID) : $('#find-page-orderby-form');
		
		//$form[0].reset();
		$form.find(':text, textarea, input[type="number"]').val('');
		$form.find(':radio, :checkbox').removeAttr('checked');
		$form.find('select').val('').change();
		$form.find('input[type=hidden]').val('');
		
		if (typeof dataTableID != 'undefined' && (typeof isReload == 'undefined' || (typeof isReload != 'undefined' && isReload != false))) {
			
			if (dataTableID in FFZX.DT) {
				FFZX.DT[dataTableID].ajax.reload();
			}			
		}		
	}
	
	FFZX.clickOnTree = function (iframeID, treeId, treeNode) {
		var $iframe = $('#' + iframeID);
		
		if ($iframe.length) {
			var $iframeDom = $iframe.contents();
			var iframeName = $iframe.attr('name');
			var $form = $iframeDom.find('#find-page-orderby-form');
			
			window.frames[iframeName].closeIframeFullPage();
			window.frames[iframeName].FFZX.resetSearch($form.attr('id'), false);	
			
			$iframeDom.find('#hidden-office-id').val(treeNode.id);
			$iframeDom.find('#find-page-orderby-button').click();
		}
	}

	/**       
	 * 对Date的扩展，将 Date 转化为指定格式的String /u516d
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)  
	 * eg:       
	 * (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
	 * (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
	 * (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
	 * (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
	 * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18 
	 * Ref: http://blog.csdn.net/vbangle/article/details/5643091 
	 */          
	Date.prototype.format=function(fmt) {           
		var o = {           
		"M+" : this.getMonth()+1, //月份           
		"d+" : this.getDate(), //日           
		"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
		"H+" : this.getHours(), //小时           
		"m+" : this.getMinutes(), //分           
		"s+" : this.getSeconds(), //秒           
		"q+" : Math.floor((this.getMonth()+3)/3), //季度           
		"S" : this.getMilliseconds() //毫秒           
		};           
		var week = {           
		"0" : "日",           
		"1" : "一",           
		"2" : "二",           
		"3" : "三",           
		"4" : "四",           
		"5" : "五",           
		"6" : "六"          
		};           
		if(/(y+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
		}           
		if(/(E+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "星期" : "周") : "")+week[this.getDay()+""]);           
		}           
		for(var k in o){           
			if(new RegExp("("+ k +")").test(fmt)){           
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
			}           
		}           
		return fmt;           
	}


	// Show mask: FFZX.mask('show');
	// Hide mask: FFZX.mask('hide');
	// Optional: title

	FFZX.mask = function(action, title){
		var $mask = $('#mask_loading');
		var $body = $('body');
		var title = (typeof title != 'undefined') ? title : '';
		var $spin = $mask.find('#canvasLoader');
		
		if (typeof action == 'undefined' || action == 'show') {
			$mask.show();
			
			if ($.trim(title) == '') {
				title = "处理中，请稍候...";		
			} 
			
			$body.append('<style>#mask_loading #canvasLoader::after{content: "' + title + '";}</style>');
			$spin.css({'margin-left': -($spin.outerWidth())/2});
			
		} else {
			$mask.hide();
		}
	}


	$(window).load(function(){
		
		var objExpandTree = {
			'wms-warehouse-handchoice': '',
			'wms-warehouse-list': ''
		};	
		
		// Expand zTree where needed
		$.each(objExpandTree, function(key, val){
			
			var arr = val.split(',');
			
			if (arr.length > 0) {
				
				$.each(arr, function(idx, ele){
					
					if (ele != '') {
						var objZtree = $.fn.zTree.getZTreeObj(ele);
						objZtree.expandAll(true);
					}
				});
			}		
		});
		
		// Select the last selected
		/*
		$('.ztree').each(function(key, val){

			var ztreeId = $(this).attr('id');
			var objZtree = $.fn.zTree.getZTreeObj(ztreeId);		
			var cookieZtreeSelectedId = FFZX.getCookie(path4Cookie + '_' + ztreeId);
			
			if (cookieZtreeSelectedId != null) {
				var selectedNode = objZtree.getNodeByTId(cookieZtreeSelectedId);
				objZtree.selectNode(selectedNode);
			}
		});
		*/
		
		if ($('.pagination').length > 0) {
			$('body').css({'overflow-y':'auto'});
		}	
	});


	//删除
	function delAccount(url, callback, isSingle) {
		var cbox =getSelectedCheckbox('', isSingle);
		
		if (cbox == "") {
			$.frontEngineDialog.executeDialogContentTime('请选择删除项！！',2000);
			return;
		}
		
		if (typeof url == 'undefined' || url == '' || url == null) {
			var url=rootPath+"/"+$("#table-deleteBtn").attr("table_action")+"?ids=";
			
			for (var i = 0; i < cbox.length; i++) {
				if(i==cbox.length-1){
					url=url+cbox[i];
				}else{
					url=url+cbox[i]+",";
				}
			}
		}
		
		$.frontEngineDialog.executeDialog('delete_table_info','信息','<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　删除之后该信息会被彻底删除,是否继续？　　','100%','100%',
			function(){
				//_tableDoSave(url,cbox);
				//window.location.href=url;
				//function(url,type,id,data,callbackfn, _callbackIframeFullPage) 
				
				if (typeof callback == 'undefined') {
					common_doSave(url,"get");
				} else {
					common_doSave(url,"get", null, null, null, callback);
				}
				return true;
			}
		);
	}

	// 删除 DataTables 的单行
	$(document).on('click', '.dataTables_wrapper tbody .action_delete', function (e) {
		e.preventDefault();
		
		var $this = $(this);
		
		// 禁用状态
		if ($this.hasClass('gray')) { return false; }
		
		var $row = $this.closest('tr');
		var $block = $this.closest('tbody');
		var url = this.href;
		var dataTableId = $this.closest('.dataTables_wrapper').attr('id').replace('dt_', '').replace('_wrapper', '');
		
		$block.find(':checkbox').prop('checked', false);
		$row.find(':checkbox').prop('checked', true);
		
		delAccount(url, function(){
			reloadData(dataTableId);
		}, 'single');	
	});


	/**
	* 获取选中的值
	*/
	var getSelectedCheckbox = function(pagId, isSingle) {
		if(pagId==''||pagId==undefined){
			pagId = 'table';
		}
		var arr = [];
		$("."+pagId+" tr td input[type='checkbox']:checkbox:checked").each(function() {
			
			if (!!isSingle) {				
				arr.push($(this).val());
				
			} else {
				
				if ($(this).is(':visible')) {
					arr.push($(this).val());
				}
			}			
		});
		return arr;
	};

	//测试验证自定义函数
	var getTest = function() {
		var isBoolean=false;
		var testVal=$("#name").val();
		if(testVal==""||testVal==null){
			isBoolean=false;
		}else{
			isBoolean=true;
		}
		return isBoolean;
	};


	/**
	* 通用保存方法
	*/
	var common_doSave = function(url,type,id,data,callbackfn, _callbackIframeFullPage) {
		type = (type==null || type=="" || typeof(type)=="undefined")? "post" : type;
		var getdata = (id==null || id=="" || typeof(id)=="undefined")? data : $('#'+id).serialize();
		var _location=window.location.href;
		 
		$.frontEngineAjax.executeAjax(
					url,
					type,
					getdata,
				   function(data){
						
						if((callbackfn==null || callbackfn=="" || typeof(callbackfn)=="undefined")){
							if (data && 'success' == data.status) {		//成功
								$.frontEngineDialog.executeDialogContentTime('成功！',1000);
								
								if (typeof _callbackIframeFullPage != 'undefined' && $.isFunction(_callbackIframeFullPage)) {
									
									setTimeout(function() {
										_callbackIframeFullPage(data);
									}, 900);
									return false;
								}
								
								if (data.url==null || data.url=="" || typeof(data.url)=="undefined") {
									setTimeout(function(){
										window.location=_location;
									}, 1000);
									
								} else {
									var ifP = data.ifParentHref;
									if(data.ifParentHref == "true"){
										setTimeout(function() {
											window.parent.location=rootPath+data.url;
										}, 1000);
										
									}else{
										setTimeout(function() {
											window.location=rootPath+data.url;
										}, 1000);
									}
								}
								
							} else if((data && 'error' == data.status)){		//错误
								$.frontEngineDialog.executeDialogContentTime(data.infoStr,4000);
							}else if((data && 'exception' == data.status)){		//异常
								$.frontEngineDialog.executeDialogContentTime(data.infoStr,4000);
							}else if((data && 'validates' == data.status)){		//验证错误多条
								var $rootInfoULi = $("#error_con ul");
								$("#error_con ul").html('');
								$("#error_con ul").show();
								$("#error_con").show();
								for (var key in data.infoMap) {
									var info = data.infoMap[key];
									// 图片路径待修改
									var infoHtml = '<li id="'+key+'-error" class="invalid" style="display: list-item;" >'+info+'</li>';
									$rootInfoULi.append(infoHtml);
								}
							}else if((data && 'validate' == data.status)){		//验证错误
								var $rootInfoULi = $("#error_con ul");
								$("#error_con ul").html('');
								$("#error_con ul").show();
								$("#error_con").show();
								var info = data.infoStr;
								// 图片路径待修改
								var infoHtml = '<li id="validateOne-error" class="invalid" style="display: list-item;" >'+info+'</li>';
								$rootInfoULi.append(infoHtml);
							
							}
						}else{
							callbackfn(data);
						}
						
						
				   },
				   function(){
						
						$.frontEngineDialog.executeDialogContentTime('出错啦！',5000);
						//$.frontEngineDialog.executeDialogContent('提示','出错啦！','200');               	
				   }
		   );
	};

	
	/* in showTree 函数中提取executeDialog 的callback 函数，并添加到树形 双击事件中(onDblClick)   2016-10-17 10:16   liuyu */
	function _ztreeChange(id,id1,call) { 

		var treeObj = $.fn.zTree
				.getZTreeObj("show_tree");
		var nodes = treeObj
				.getSelectedNodes();
		if (nodes != null && nodes != "") {
			if(nodes[0].name=="仓库"){
				$.frontEngineDialog.executeDialogContentTime('仓库根节点不能为作业仓库,请重新选择!');
				return false;
			}
			$("#" + id).val(nodes[0].name);
			$("#" + id1).val(nodes[0].id);

			if (call == null
					|| call == ""
					|| typeof (call) == "undefined") {
				 var $close=$(document.getElementById('title:showTree')).prev();
				  $close.click();
			} else {
				if (Object.prototype.toString
						.call(call) === '[object Function]') {
					call;
				} else {
					var fn = eval(call);
					fn();

				}
			}
		}
	
	}
	/**
	 * 弹出树形选择
	 * title:弹出的标题
	 * url：树形地址
	 * id：操作元素弹出树形的id
	 * id1：选择树形id保存在id1元素上
	 * call：单击确定调用的函数(可以传函数或者函数名)
	 * pId:父节点id，默认pId
	 */
	/**
	 * 弹出树形选择 title:弹出的标题 url：树形地址 id：操作元素弹出树形的id id1：选择树形id保存在id1元素上
	 * call：单击确定调用的函数(可以传函数或者函数名) pId:父节点id，默认pId
	 */
	function showTree(title, url, id, id1, call, pId) {
		pId = (pId == null || pId == "" || typeof (pId) == "undefined") ? "pId"
				: pId;
		var tree_setting = {
			data : {// 数据
				simpleData : {
					enable : true,// true / false 分别表示 使用 / 不使用 简单数据模式
					// 默认false，一般使用简单数据方式
					idKey : "id",// 节点数据中保存唯一标识的属性名称 默认值："id"
					pIdKey : pId
				// 点数据中保存其父节点唯一标识的属性名称 默认值："pId"
				},
				key : {
					url : ""
				}
			},
			view : {
				selectedMulti : false
			},
			callback: {
				onDblClick: function(){_ztreeChange(id,id1,call);}
			}
		};
		$("#" + id).blur();
		$.frontEngineAjax
				.executeAjaxPost(
						rootPath + url,
						"",
						function(ret) {
							var content = '<div class="showTreeBackground left"><div><input type="text" class="form-control input-sm txt_mid" style="height:31px;" id="shwo_tree_search_text"><button id="show_tree_search_btn" class="btn btn-primary btn-sm" style="margin-left:5px;" onclick="showTreeSearchClick()"><i class="fa fa-search"></i></button><div><ul id="show_tree" class="ztree"></ul></div>';
							$.frontEngineDialog
									.executeDialog(
											'showTree',
											title,
											content,
											"400px",
											"430px",
											function() {_ztreeChange(id,id1,call);});
							$.fn.zTree.init($("#show_tree"), tree_setting, ret);
						}, function(err) {
							$.frontEngineDialog.executeDialogOK('错误', err);
						});
	}
	/**
	 * @param value 传入要转换的字符串
	 * @param fun 传入结果处理方法,方法第一个参数为转换结果r,r.simple为简拼,r.full为全拼
	 * */
	function toPinyin(value,fun){
		if(value!=null && value!=''){
			$.post(rootPath+'/framework/pinyin/getPinyin.do',{value:value},function(res){
				if(typeof(fun)=='function'){
					fun.call(null,res);
				}
			},'json');
		}
	}
	/**
	 * 设置简拼的值
	 * @param chineseComp  输入中文的对象
	 * @param simplePinyinComp  简拼组件
	 */
	function setSimplePinyinValue(chineseComp,simplePinyinComp){
		setPinyinValue(chineseComp, simplePinyinComp, true);
	}
	/**
	 * 设置简拼的值
	 * @param chineseComp  输入中文的对象
	 * @param simplePinyinComp  简拼组件
	 */
	function setFullPinyinValue(chineseComp,fullPinyinComp){
		setPinyinValue(chineseComp, fullPinyinComp, false);
	}
	/**
	 * 设置值
	 * @param chineseComp  中文组件
	 * @param pinyinComp  拼音组件 
	 * @param flag  false:表示是全拼，true表示是简拼
	 */
	function setPinyinValue(chineseComp,pinyinComp,flag){
		$(chineseComp).bind('change',function(){
			toPinyin($(this).val(),function(result){
				$(pinyinComp).val(flag?result.simple:result.full);
			});
		});
	}



	/**
	 * 树形内的 点击查询事件
	 */
	$("#treeSearch").click(function(){
		var name = $('#fuzzyQueryName').val();
		treeLocate('left_menu_tree','name',name);
	})
	var treeLocateCache = {};
	/**
	 * show_tree树形内的 点击查询事件
	 */
	function showTreeSearchClick() {
		var name = $('#shwo_tree_search_text').val();
		treeLocate('show_tree', 'name', name);
	}

	/**
	 * 模糊查询函数
	 */
	function treeLocate(treeId,key,value){
		if(treeId){
			var tree = $.fn.zTree.getZTreeObj(treeId);
			if(tree){
				if(!treeLocateCache[treeId]){
					treeLocateCache[treeId] = {};
				}
				if(key && value){
					var idx = 0;
					if(treeLocateCache[treeId].key==key && treeLocateCache[treeId].value==value){
						idx = treeLocateCache[treeId].index || 0;
					}else{
						treeLocateCache[treeId].index = 0;
						treeLocateCache[treeId].key = key;
						treeLocateCache[treeId].value = value;
					}
					var nodes = tree.getNodesByParamFuzzy(key, value, null);
					if(nodes.length > 0){
						if(idx >= nodes.length){
							idx = 0;
						}
						if(idx < nodes.length){
							tree.expandNode(nodes[idx], true, false, true);
							tree.selectNode(nodes[idx]);
							treeLocateCache[treeId].index = ++idx;
						}
					}
				}else{
					treeLocateCache[treeId] = {};
				}
			}
		}
	}

	/**
	 * 编辑或新增返回提示
	 */
	function isReturn(url){
		//确认放弃当前录入内容
		$.frontEngineDialog.executeDialog('isReturn_table_info','信息','<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　是否确定放弃当前录入信息？　　','100%','100%',
			function(){
				if(url != '' && url != null){
					location.href = url;
				}else{
					history.go(-1);
				}
			}
		);    
	}


	/**
	 * 在当前页面下载文件
	 * url: post 到的地址
	 * params: JSON 格式的一组参数
	 */
	FFZX.download = function(url, params){
		
		var $form = $("<form />"); //定义一个form表单
		
		$form.attr("style","display:none");
		$form.attr("target","");
		$form.attr("method","post");
		$form.attr("action",url);
		
		if (params) {
			
			for (var pk in params) {
				var paramInput=$("<input />");
				paramInput.attr("type", "hidden");
				paramInput.attr("name", pk);
				paramInput.attr("value", params[pk]);
				$form.append(paramInput);
			}
		}
		
		$("body").append($form);
		$form.submit(); //表单提交 
	}

	/**
	 * 只作返回提示信息，回退到上一步
	 */
	FFZX.isReturnMsg=function(title,context){
		$.frontEngineDialog.executeDialog('isReturn_table_info',title,'<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　'+context+'　　','100%','100%',
				function(){
					history.go(-1);
				}
			);
		
	}



	//delete spaces before and after
	FFZX.trim = function(str) {
		if (FFZX.isEmpty(str)) return "";
		return str.replace(/(^\s*)|(\s*$)/g, "");	
	};
	//judgment is empty
	FFZX.isEmpty = function(obj) {
		return obj === null || obj === "" || obj === "null" || typeof(obj) === "undefined";
	};

	//blur :verify that the input is an integer 
	FFZX.blurvalidationInteger = function(inputlb){
		 var faceValue=$(inputlb).val();
		 var ex = /^\d+$/;
		 if(FFZX.trim(faceValue)){
		 //if(faceValue === null || faceValue === "" || faceValue === "null" || typeof(faceValue) === "undefined"){
			 if(!ex.test(faceValue)){
					$.frontEngineDialog.executeDialogOK('提示信息','面值只能是整数！','300px');
					$(inputlb).val("");
				 }
		 }
	}
	//blur :validate input contrast
	FFZX.blurvalidationIntegerContrast = function(faceid,maxFaceid){
		 var faceValue=$("#"+faceid).val();
		 var maxFaceValue=$("#"+maxFaceid).val();
		 if(faceValue!=null && maxFaceValue!=null){
			 if(parseInt(maxFaceValue)<parseInt(faceValue)){
				$.frontEngineDialog.executeDialogOK('提示信息','面值第二次输入不得小于第一次！','300px');
				$("#"+maxFaceid).val('');
			 }	
		 }
	}

	// Set cookie value
	FFZX.setCookie = function(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays*24*60*60*1000));
		var expires = "expires="+d.toUTCString();
		document.cookie = cname + "=" + cvalue + "; " + expires + '; path=' + rootPath;
	}

	// Get cookie value
	FFZX.getCookie = function(cname){
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1);
			if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		}
		return null;
	}

	// 监听 iframe 内页面是否加载完毕
	// document.cookie = 'wms-global-op-whnm=abc123;wms-param=def456'
	// document.cookie = 'wms-global-op-whnm='
	var cacheBeforeUnload = window.onbeforeunload;

	FFZX.funcBeforeUnload = function(){
				
		pageHistoryPrev = FFZX.setCookie(cookie_page_prev, self.window.location.href, 1);
		
		if ($.isFunction(cacheBeforeUnload)) {
			cacheBeforeUnload();
		}
		
		if (getCurIframeName != '') {

			var $iframe = $('iframe[id="' + curIframeID + '"]');
			
			if ($iframe.length) {
				$iframe.css("visibility", "hidden");
				
			} else {
				
				if (curIframeObj[1].indexOf(getCurUrl) == -1) {
					parent.postMessage(curIframeID+',hidden', curIframeObj[1]);
					
				} else {
					var $parentIframe = $('iframe[id="' + curIframeID + '"]', parent.document);					
				
					$parentIframe.css("visibility", "hidden");
					
					var wrapper = $parentIframe.parent()[0];
					
					if (typeof wrapper != 'undefined' && typeof wrapper.id != 'undefined' && wrapper.id === 'content:'+curIframeID) {
						
						var iframe_next = $parentIframe.siblings(".currighttag")[0];
						
						if (iframe_next === null || iframe_next === "" || iframe_next === "null" || typeof(iframe_next) === "undefined") {
							var $iframe_div_id = $parentIframe.parent()[0];
							$($iframe_div_id).append(righttag);
							
						} else {
							$(iframe_next).css("display","block");
						}
						
					}else{
					
						if ($('.currighttag', parent.document).length) {
							$('.currighttag', parent.document).css("display","block");
							
						} else {
							
							if ($('.md-loading', parent.document).length) {
								//$('.md-loading', parent.document).css("display","block");
								
							} else {									
								$('body', parent.document).append(righttag);
							}
						}
					}
				}
			}			
		}
	};
	// End: window.onbeforeunload

	FFZX.listenToIframe = function() {
		
		"use strict";

		var top_curr_warehouse = FFZX.getCookie('wms-global-op-whnm');	
		top_curr_warehouse = (top_curr_warehouse == null)?'':unescape(top_curr_warehouse);

		$(document).ready(function() {
		
			if ($('.currighttag').length == 0){	
				$('body').append(righttag);
			}	

			if (curIframeID != '' && top_href != '') {
			
				$(window).load(function(){				
					
					// 如果同域
					if (getCurIframeName.indexOf(getCurUrl) != -1) {
						
						// 如果是 iframe 中的 iframe，父 iframe 无加载监听
						if (isDeeperIframe) {
							parent.$('.md-loading').hide();
							parent.$('iframe[name="' + getCurIframeName + '"]').css({'visibility':'visible'}).show();					
						}
					}			
				
					$('body').append('<style>.md-loading{display:none !important}</style>');
					parent.postMessage(curIframeID+',show,' + top_curr_warehouse, top_href);
				});
			}
		});			
		
		window.onunload = FFZX.funcBeforeUnload;
		
	};

	// 跳过登入的首页
	if (self.window != top.window) {
		FFZX.listenToIframe();
	}

	$(window).load(function(){
		
		if (parent.window) {
			
			if (getCurIframeName.indexOf(getCurUrl) != -1) {
				parent.$('#mask_loading').hide();
			}
		}
		
		// 防止重复点击按钮
		var timeout_disable_btn = null;
		
		$('.btn').bindFirst('click', function(e){
			var $btn = $(this);
			var clickedTimes = $btn.data('clickedTimes') || 0;
			clickedTimes = parseInt(clickedTimes);
			clickedTimes++;
			
			$btn.data('clickedTimes', clickedTimes);
			clearTimeout(timeout_disable_btn);

			timeout_disable_btn = setTimeout(function(){
				$btn.data('clickedTimes', 0);
			}, 500);
			
			if ($btn.data('clickedTimes') > 1) {
				e.stopImmediatePropagation();				
				return false;				
			}
		});
	});

	function reloadData(dataTableId, back2home){
		// Ref: https://datatables.net/reference/api/ajax.reload()
		// 保留在当前页刷新
		
		var _back2home = (typeof back2home != 'undefined' && back2home == true) ? true : false;
		
		if (typeof dataTableId != 'undefined' && dataTableId != '') {			
			var id = 'dt_'+ dataTableId;

			if (id in FFZX.DT) {
				FFZX.DT[id].ajax.reload(null, _back2home);
			}
		}
	}

	function closeIframeFullPage(dataTableId){
		$('.iframeFullPage').remove();
		$('body').removeClass('noScroll');
	}

	// 方法：生成整页的 iframe 浮动窗口
	function iframeFullPage(url){

		$('body').addClass('noScroll');
		
		var $iframe = $('.iframeFullPage');
		var winHeight = $(self.window).height();
		var winName = 'ifp' + url.split('?')[0].replace(/[\W]/ig, '_');
		
		if ($iframe.length) {
			$iframe.remove();
		}
		
		var iframe = '<iframe frameborder="0" scrolling="auto" src="'+ url +'" name="' + winName + ','+ window.location.href + '" class="iframeFullPage" style="height:' + winHeight + 'px">';
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

	function postCmd(data) {
		
		var _data = ($.type(data) == 'object') ? JSON.stringify(data) : data;
		parent.postMessage(_data, curIframeObj[1]);
	}

	// 转换表单数据为 JSON 对象
	// Thanks to http://www.cnblogs.com/hyl8218/archive/2013/06/27/3159178.html
	!function(a){a.fn.form2Json=function(){var b={},c=this.serializeArray();return a(c).each(function(){b[this.name]?a.isArray(b[this.name])?b[this.name].push(this.value):b[this.name]=[b[this.name],this.value]:b[this.name]=this.value}),b}}(jQuery);

	/*
	 * jQuery.bind-first library v0.2.3
	 * Copyright (c) 2013 Vladimir Zhuravlev
	 * https://github.com/private-face/jquery.bind-first
	 * Released under MIT License
	 * @license
	 * Date: Thu Feb  6 10:13:59 ICT 2014
	 **/
	(function(t){function e(e){return u?e.data("events"):t._data(e[0]).events}function n(t,n,r){var i=e(t),a=i[n];if(!u){var s=r?a.splice(a.delegateCount-1,1)[0]:a.pop();return a.splice(r?0:a.delegateCount||0,0,s),void 0}r?i.live.unshift(i.live.pop()):a.unshift(a.pop())}function r(e,r,i){var a=r.split(/\s+/);e.each(function(){for(var e=0;a.length>e;++e){var r=t.trim(a[e]).match(/[^\.]+/i)[0];n(t(this),r,i)}})}function i(e){t.fn[e+"First"]=function(){var n=t.makeArray(arguments),i=n.shift();return i&&(t.fn[e].apply(this,arguments),r(this,i)),this}}var a=t.fn.jquery.split("."),s=parseInt(a[0]),f=parseInt(a[1]),u=1>s||1==s&&7>f;i("bind"),i("one"),t.fn.delegateFirst=function(){var e=t.makeArray(arguments),n=e[1];return n&&(e.splice(0,2),t.fn.delegate.apply(this,arguments),r(this,n,!0)),this},t.fn.liveFirst=function(){var e=t.makeArray(arguments);return e.unshift(this.selector),t.fn.delegateFirst.apply(t(document),e),this},u||(t.fn.onFirst=function(e,n){var i=t(this),a="string"==typeof n;if(t.fn.on.apply(i,arguments),"object"==typeof e)for(type in e)e.hasOwnProperty(type)&&r(i,type,a);else"string"==typeof e&&r(i,e,a);return i})})(jQuery);
	
} else {
	
	console && console.log('前端警示：全局的 common.js 被多次加载，请去重！问题页面：' + window.location.href);
}



/**
 * FORM表单数据填充
 */
$.fn.extend({
    //表单加载json对象数据
    setForm : function (data) {
        var obj = this;
        var jsonValue = data.dataObject;
        var protectColumn = data.protectColumn;
        $.each(jsonValue, function (name, ival) {
            var $oinput = obj.find("input[name=" + name + "]");
            if ($oinput.attr("type") == "checkbox") {
                if (ival !== null) {
                    var checkboxObj = $("[name=" + name + "]");
                    var checkArray = ival.split(";");
                    for (var i = 0; i < checkboxObj.length; i++) {
                        for (var j = 0; j < checkArray.length; j++) {
                            if (checkboxObj[i].value == checkArray[j]) {
                                checkboxObj[i].click();
                            }
                        }
                    }
                }
            }
            else if ($oinput.attr("type") == "radio") {
                $oinput.each(function () {
                    var radioObj = $("[name=" + name + "]");
                    for (var i = 0; i < radioObj.length; i++) {
                        if (radioObj[i].value == ival) {
                            radioObj[i].click();
                        }
                    }
                });
            }
            else if ($oinput.attr("type") == "textarea") {
                obj.find("[name=" + name + "]").html(ival);
                if($.inArray(name, protectColumn)>=0){
                	obj.find("[name=" + name + "]").attr("readonly",true);
                }
            }
            else {
                obj.find("[name=" + name + "]").val(ival);
                if($.inArray(name, protectColumn)>=0){
                	obj.find("[name=" + name + "]").attr("readonly",true);
                }
            }
        })

    }
});
// END: checking duplicate including