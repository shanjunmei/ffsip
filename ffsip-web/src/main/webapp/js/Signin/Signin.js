function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


$(function () {
    var wxOpenid = getUrlParam('code');
    var refer=getUrlParam("refer");

    if (!wxOpenid) {
        $.alert('网络出了点小问题');
        location.href = 'index.html';
    }
    common_ajax({
        url: _pathTotal + 'login.do', data: {'wxOpenid': wxOpenid,'refer':refer}, success: function (res) {

            if (res) {
                var index='index.html';
                if(res.refer){
                    index=res.refer;
                }
                location.href = index;
            } else {
                $.alert('自动登录失败，请重试');
            }

        }
    });

});

