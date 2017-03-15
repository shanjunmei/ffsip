function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


$(function () {
    var authCode = getUrlParam('code');
    var refer=getUrlParam("refer");

    if (!authCode) {
        $.alert('网络出了点小问题');
        location.href = 'index.html';
    }
    common_ajax({
        url: _pathTotal + 'login.do', data: {'authCode': authCode,'refer':refer}, success: function (res) {

            if (res) {
                var index='index.html';
                if(res.refer){
                    index=res.refer;
                }
                if(res.code==-3){
                    console.log(res);
                    window.close();
                    return;
                }
                location.replace(index);
            } else {
                $.alert('自动登录失败，请重试');
            }

        }
    });

});

