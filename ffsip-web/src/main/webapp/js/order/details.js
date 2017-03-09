$(function () {
    initialOrderDetail();
})

//马上购买支付函数
function kartpay(orderNo, callback) {
    console.log('支付接口');

    //location.href = 'orderDetails.html?id='+orderNo;


    $.ajax({
        type: "get",
        url: _pathTotal + 'orderCharge.do?orderNo=' + orderNo,
        data: orderNo,
        dataType: 'json',
        success: function (rsp) {
            console.log(rsp);
            if(rsp.errorCode){
                $.alert(rsp.msg);
                return;
            }
            pingpp.createPayment(rsp, function (result, err) {
                console.log(result);
                console.log(err.msg);
                console.log(err.extra);
                if (err.msg) {
                    $.alert(err.msg);
                }
                window.location.reload();
                if (result == "success") {
                    // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL。
                    console.log("支付成功");

                    if (null != callback) {
                        callback(true);
                    }
                } else if (result == "fail") {
                    console.log("付款失败" + err.msg + err.extra);
                    if (null != callback) {
                        callback(false);
                    }
                } else if (result == "cancel") {
                    console.log("取消付款");
                    if (null != callback) {
                        callback(false);
                    }
                }
            });


        }
    })


}


function initialOrderDetail() {
    var pageTabId = $.frontEngine.getUrlParameter('id');
    var _basePath = _pathTotal + 'orderDetail.do?orderCode=' + pageTabId;
    var _getSInfoJson = {};
    common_ajax({
        url: _basePath, success: function (getSInfoResult) {
            //$.frontEngineAjax.executeAjax(_basePath,"get",_getSInfoJson,function(getSInfoResult){

            //console.log(getSInfoResult);
            var _useTime = new Date(getSInfoResult.orderInfo.startTime).format('yyyy-MM-dd') + ' ' + getSInfoResult.game.name + '（' + new Date(getSInfoResult.orderInfo.startTime).format('hh:mm') + '）';
            var _payTime = new Date(getSInfoResult.orderInfo.payTime).format('yyyy-MM-dd hh:mm:ss');
            var _status = '';
            if (getSInfoResult.useState === "1") {	//未使用
                _status = '未使用';
            } else {//已使用
                _status = '已使用';
            }
            var _html = '<div class="ticket div-bg"><h3>' + getSInfoResult.orderInfo.orderName + '</h3><ul class="price-ul"><li>RMB：<span class="price">' + getSInfoResult.orderInfo.favorablePrice + '</span></li><li>门市价：RMB：<span>' + getSInfoResult.orderInfo.actualPrice + '</span></li></ul>';

            _html = _html + '<div class="address bor1"><i class="ic1"><img src="images/ico1.png"></i><p>深圳市南山区海德大道中68海的农场旁200米红路灯处</p></div>';


            _html = _html + '<ul class="qita-ul"><li><i class="ic2"><img src="images/ico2.png"></i>时长:' + getSInfoResult.game.time + '分钟</li><li><i class="ic3"><img src="images/ico3.png"></i>过期自动退</li><li><i class="ic4"><img src="images/ico4.png"></i>消费者保障</li></ul></div>';


            _html = _html + '<div class="order-num div-bg"><ul class="order-num-ul"><li class="bor1"><div class="order-num-div">仙女湖国际卡丁车 | 订单号：<span>' + getSInfoResult.orderInfo.code + '</span></div>';
            if (getSInfoResult.orderInfo.payTime) {//如果已经支付，则显示验证码
                _html = _html + '<div class="code">验证码：<span class="code-num">' + getSInfoResult.orderInfo.verificationCode +'</span><span class="ifuse">'+ _status +'</span> </div>';
                _html=_html+'<div class="code">（温馨提示:入场请出示验证码！）</div>';
            }

            _html = _html + '</li>';

            _html = _html + '<li>手机号码：' + getSInfoResult.orderInfo.memberPhone + '</li>';
            _html = _html + '</ul></div>';

            _html = _html + '<div class="order-info div-bg"><ul class="order-ul"><li>场次：<span class="screenings-time">' + _useTime + '</span></li><li class="num">数　量：<div class="num-div"><span>X' + getSInfoResult.orderInfo.buyCount + '</span></div></li><li>总　价：<div class="price-total">RMB：<span>' + getSInfoResult.orderInfo.totalPrice + '</span></div></li></ul></div>';

            if (!getSInfoResult.orderInfo.payTime) {
                _html = _html + '<div class="btn"><a href="javascript:kartpay(\'' + getSInfoResult.orderInfo.code + '\');" class="btn-buy">立即支付</a></div>';
            }
            $('.order').html(_html);

        }
    })
}
