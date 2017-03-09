$(function () {
	
	var userState = $.frontEngine.getUrlParameter('type');
	
	initialTitle(userState);
	
    initialOrderList(userState);
    
    $('.order-list-title li').on('click',function(){
    	if($('a',this).hasClass('now')){
    		return;
    	}
    	$('.order-list-title li a').removeClass('now');
    	$('a',this).addClass('now');
    	
    	var _type = $(this).attr('data-type');
    	
    	/*var _url = window.location.href;
    	var reqestType=$.frontEngine.getUrlParameter('type');
    	if(!(typeof(_type) == "undefined")){
    		_url=_url.replace();
    	}*/
    	//if(!(typeof(_type) == "undefined")){
			 initialOrderList(_type);
		//}
    
    });
})

function initialTitle(type){
	$('.order-list-title li a').removeClass('now');
	if(!(typeof(type) == "undefined") && type != null ){
			if(type == 0){
				 $($('.order-list-title li a')[1]).addClass('now');
			}else if(type == 1){
				 $($('.order-list-title li a')[2]).addClass('now');
			}else if(type == 2){
				 $($('.order-list-title li a')[3]).addClass('now');
			}else if(type == 3){
				 $($('.order-list-title li a')[4]).addClass('now');
			}
	}else{
		 $($('.order-list-title li a')[0]).addClass('now');
	}
}

function initialOrderList(userState) {
    
    var _basePath = _pathTotal + 'myOrder.do';
    if(!(typeof(userState) == "undefined")){
        _basePath = _basePath + '?useState=' + userState;
    }
    var _getSInfoJson = {};
    common_ajax({
        url: _basePath, success: function (getSInfoResult) {

            //$.frontEngineAjax.executeAjax(_basePath,"get",_getSInfoJson,function(getSInfoResult){


            var _html = '';
            var $listObj = $('.center');
            for (var i = 0; i < getSInfoResult.length; i++) {
                var _itmeOB = getSInfoResult[i];
                var _useTime = new Date(_itmeOB.startTime).format('yyyy-MM-dd');
                var _status = _itmeOB.useState;
                if (_itmeOB.startTime < new Date().getTime()) {
                    _status = '3';
                }


                if (_status === "0") {	//未使用
                    _status = '未使用';
                    _html = _html + '<div class="order-list">';
                } else if (_status=== "1") {//已使用
                    _status = '已使用';
                    _html = _html + '<div class="order-list used">';
                } else {
                    _status = '已过期';
                    _html = _html + '<div class="order-list">';
                }

                var _payStatus = _itmeOB.status;		//支付状态
                if (_payStatus == '0') {
                    _payStatus = '未支付';
                } else if (_payStatus == '1') {
                    _payStatus = '已支付';
                } else if (_payStatus == '2') {
                    _payStatus = '已退款';
                }
                _html = _html + '<a href="orderDetails.html?id=' + _itmeOB.code + '"><h3>' + _itmeOB.orderName + '</h3><span> 订单号:' + _itmeOB.code + '</span> <ul class="orderlist-ul"><li>数量：' + _itmeOB.buyCount + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价：' + _itmeOB.totalPrice + '元</li><li>场次时间：' + _useTime + '   </li></ul><p class="no-use">' + _status + '</p><p class="pay-status">' + _payStatus + '</p></a></div>';
            }

			if(_html == ''){
				_html = '<div class="no_data">没有订单...</div>';
			}
            $listObj.html(_html);

        }
    })
}
