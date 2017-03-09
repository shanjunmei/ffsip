$(function () {


    userInfo();


})


function userInfo() {
    var _basePath = _pathTotal + 'memberInfo.do';
    var $userObj = $('.header');
    common_ajax({
        url: _basePath, async: false, success: function (data) {
            userRender($userObj, data);
        }
    });
}

function userRender(target, data) {
    console.log(data);
    var headPic = "images/tx.jpg";
    if (data.wxHeadimgurl) {
        headPic = data.wxHeadimgurl;
    }
    var _name = data.wxNickName;
    var _phone = data.phone;
    var _html = '<img src="images/bg1.png" class="bg1"><div class="tx"><img src="' + headPic + '"></div><div class="nickname">昵称：' + _name + '</div>';

    //+'<div class="userStyle"><span>普通会员</span></div>';
    if (_phone) {
        _html = _html + '<div class="per-tel"><a href="javascript:void(0);">手机号码:' + _phone + '  </a></div>';
    }
    target.html(_html);
    console.log(_html);
    orderInfo()
}


function orderInfo() {
    var _basePath = _pathTotal + 'orderInfo.do';
    var _orderInfo = $('.preordain-ul');
    common_ajax({
        url: _basePath, async: false, success: function (data) {
         /*   var _html = '<li onclick="toOrderList(0)"><i class="ico8" ><img src="images/ico8.png" ></i>可用订单(' + data.unUsed + ')</li>' +
                '<li onclick="toOrderList(1)"><i class="ico9" ><img src="images/ico9.png" ></i>已使用(' + data.used + ')</li>';
           */
           var _html = '<li onclick="toOrderList(0)"><i class="ico8"><img src="images/ico12.png"></i>未支付 ('+data.noPay+')<span class="link-right"><img src="images/ico5.png"></span></li>'+
				        '<li onclick="toOrderList(1)"><i class="ico8"><img src="images/ico13.png"></i>未使用 ('+data.noUsed+')<span class="link-right"><img src="images/ico5.png"></span></li>'+
				        '<li onclick="toOrderList(2)"><i class="ico8"><img src="images/ico14.png"></i>已使用 ('+data.used+')<span class="link-right"><img src="images/ico5.png"></span></li>'+
				        '<li onclick="toOrderList(3)"><i class="ico8"><img src="images/ico15.png"></i>已失效 ('+data.invalid+')<span class="link-right"><img src="images/ico5.png"></span></li>';
           
           _orderInfo.html(_html);
            console.log(_html);
        }
    });
}