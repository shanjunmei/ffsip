/**
 * Created by yu.liu on 2016/2/2.
 */

/**
 * ***************************************************************************
 * 前端页面js引擎
 *      ***************************************************************************
 */
// TODO 格式还需进一步修改，采用strict方式。
(function($) {



    /**
     * dialog封装
     * id   设定对话框唯一标识。
     * title    标题内容。
     * fixed (默认值: false) 开启固定定位。
     * resize (默认值: true)  是否允许用户调节尺寸
     * drag   (默认值: true) 是否允许用户拖动位置
     * lock   (默认值: false) 开启锁屏。中断用户对话框之外的交互，用于显示非常重要的操作/消息，所以不建议频繁使用它，它会让操作变得繁琐
     * content  	消息内容。1、如果传入的是HTMLElement类型，如果是隐藏元素会给其设置display:block以显示该元素，其他属性与绑定的事件都会完整保留，对话框关闭后此元素又将恢复原来的display属性，并且重新插入原文档所在位置
     *                          2、如果没有设定content的值则会有loading的动画
     * ifurl   iframe发送请求的地址
     * ifwidth     iframe宽度
     * ifheight     iframe高度
     * successfn 成功回调函数
     * 
     * showModal  模态对话框 (有屏蔽层)
     */
	//内嵌页面
    var executeIframeDialog=function(id,title,ifurl,ifwidth,ifheight,fixed,resize,drag,lock) {
        fixed = (fixed==null || fixed=="" || typeof(fixed)=="undefined")? "false" : fixed;
        resize = (resize==null || resize=="" || typeof(resize)=="undefined")? "true" : resize;
        drag = (drag==null || drag=="" || typeof(drag)=="undefined")? "true" : drag;
        lock = (lock==null || lock=="" || typeof(lock)=="undefined")? "false" : lock;
        id = (id==null || id=="" || typeof(id)=="undefined")?  new Date().getTime() : id;

        dialog({
            id: id,
            title: title,
            fixed: fixed,
            resize: resize,
            drag: drag,
            lock: lock,
            content:"<iframe id='"+id+"' name='"+id+","+window.location.href+"' src='"+ifurl+"' style='visibility: hidden;' width='"+ifwidth+"' height='"+ifheight+"' frameborder='0' scrolling='auto'></iframe>"
            /*onclose: function () {
				if (this.returnValue) {
					//console.log($(this.returnValue));
					$(this.returnValue).click();
				}
			}*/
        }).showModal();

    };

    //有确认键和取消键，确认键有函数
    var executeDialog=function(id,title,content,width,height,callback) {
    	id = (id==null || id=="" || typeof(id)=="undefined")?  new Date().getTime() : id;
        dialog({
            id: id,
            icon:'succeed',
            title: title,
            width:width,
            height:height,
            content: content,
            button: [
                     {
                         value: '确定',
                         callback: callback
                     },
                     {
                         value: '取消'
                     }
                     
                 ]
            /*cancelValue: '取消',
            cancel: function () {},
            okValue: '确定',
            ok:callback*/
           /* ok:callback,
            cancel: true*/
        }).showModal();

    };
    
    //只有确定键
    var executeDialogOK=function(title,content,width) {
        dialog({
            title: title,
            content: content,
            width:width,
            okValue: '确定',
            ok: function () {autofocus: false}
        }).showModal();

    };
    
  //固定在触发的按钮旁版本
    var executeClickLableDialog=function(title,content,clickLable,width) {
        dialog({
            title: title,
            content: content,
            width:width
        }).showModal(clickLable);

    };
    //普通提示语
    var executeDialogContent=function(title,content,width) {
    	 dialog({
    		 id: 'Promptlanguage',
             title: title,
             content: content,
             width:width
         }).showModal();

    };
    //定时普通提示语
    var executeDialogContentTime=function(content,time) {
    	time = (time==null || time=="" || typeof(time)=="undefined")? 2000 : time;
        var dContent = dialog({
        	id: 'PromptlanguageTime',
    	    content: content,
    	    quickClose: true
    	});
        dContent.showModal();
    	setTimeout(function () {
    		dContent.close().remove();
    	}, time);

    };
    
    //定时普通提示语no quickClose
    var executeDialogContentTimeNoQC=function(content,time) {
    	time = (time==null || time=="" || typeof(time)=="undefined")? 2000 : time;
        var dContentNoQC = dialog({
        	id: 'PromptlanguageTimeNoQC',
    	    content: content
    	});
        dContentNoQC.showModal();
    	setTimeout(function () {
    		dContentNoQC.close().remove();
    	}, time);

    };
   
    // ****************************************************************************************************
    // $.frontEngine.methodName形式调用
    // ****************************************************************************************************
    $.extend({
        frontEngineDialog: {
            executeIframeDialog: function(id,title,ifurl,ifwidth,ifheight,fixed,resize,drag,lock) {
                return executeIframeDialog(id,title,ifurl,ifwidth,ifheight,fixed,resize,drag,lock);
            },
            executeDialog: function(id,title,content,width,height,callback){
                return executeDialog(id,title,content,width,height,callback);
            },
            executeDialogOK: function(title,content,width){
                return executeDialogOK(title,content,width);
            },
            executeClickLableDialog: function(title,content,clickLable,width){
                return executeClickLableDialog(title,content,clickLable,width);
            },
            executeDialogContent: function(title,content,width){
                return executeDialogContent(title,content,width);
            },
            executeDialogContentTime: function(content,time){
                return executeDialogContentTime(content,time);
            },
            executeDialogContentTimeNoQC: function(content,time){
                return executeDialogContentTimeNoQC(content,time);
            }

        }
    });


}) (jQuery);






