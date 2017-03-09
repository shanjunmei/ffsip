$(function () {
    initialSiteNav();


})

function initialSiteList(value) {

    console.log(value);
    //var pageTabId =  $.frontEngine.getUrlParameter('id');
    var _basePath = _pathTotal + 'games.do?effectiveTime=' + value;
    var _getSInfoJson = {};
    var slhtml = "";
    var $list = $('.reserve_list');
    var _typeList = {'0': '公共车(入门级)', '1': '公共车(专业级)', '2': '自备车'};				//0:公共车(入门级),1:公共车(专业级),2:自备车',
    common_ajax({
        url: _basePath, success: function (getSInfoResult) {

            //$.frontEngineAjax.executeAjax(_basePath,"get",_getSInfoJson,function(getSInfoResult){

            //slhtml  = slhtml + '<dl><dt><span class="time1">09：05</span>01场<span class="reserve_txt">时长15分钟</span></dt><dd>公租车（入门级）<span class="reserve_txt">名额：0      20/20</span></dd><a href="javascrit:void(0);" class="reserve_button no">选 择</a></dl>';
            for (var i = 0; i < getSInfoResult.length; i++) {
                var _itemObj = getSInfoResult[i];
                var _number = parseInt(_itemObj.bespeakNum) - parseInt(_itemObj.participantsNumber);

                var canSelect = true;
                var invalid = false;
                if (parseInt(_itemObj.participantsNumber) >= parseInt(_itemObj.bespeakNum)) {
                    canSelect = false;
                }
                var current = new Date().getTime();
                var startTime = _itemObj.startTime;
                if (current > startTime) {
                    invalid = true;
                }

                if (!invalid) {
                    slhtml=slhtml+'<dl><dt><ul><li> <span class="time1">'+_itemObj.gameTime.substring(0,5)+'</span><span class="reserve_txt">时长'+_itemObj.time+'分钟</span></li><li class="li2">'+_typeList[_itemObj.type]+'<span class="reserve_txt">名额：      '+_itemObj.participantsNumber + '/' + _itemObj.bespeakNum +'</span></li></ul></dt><dd>¥<b>'+_itemObj.preferentialPrice+'</b><span class="reserve_txt">门市价：¥'+_itemObj.retailPrice+'</span></dd>';
                    //slhtml = slhtml + '<dl><dt><span class="time1">' + '' + '</span>' + _itemObj.name + '<span class="reserve_txt">时长' + _itemObj.time + '分钟</span></dt><dd>' + _typeList[_itemObj.type] + '<span class="reserve_txt">名额：' + _number + '      ' + _itemObj.participantsNumber + '/' + _itemObj.bespeakNum + '</span></dd>';
                }
                if (!invalid) {
                    if (canSelect) {
                        slhtml = slhtml + '<a href="orderConfirm.html?id=' + _itemObj.code + '" class="reserve_button">选 择</a></dl>';
                    } else {
                        slhtml = slhtml + '<a href="javascrit:void(0);" class="reserve_button no">选 择</a></dl>';
                    }
                }

            }
            $list.html(slhtml);
        }
    })


}
// 日期，在原有日期基础上，增加days天数，默认增加1天
function addDate(date, days) {
    if (days == undefined || days == '') {
        days = 0;
    }
    var date = new Date(date);
    date.setDate(date.getDate() + days);
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
}

// 日期月份/天的显示，如果是1位数，则在前面加上'0'
function getFormatDate(arg) {
    if (arg == undefined || arg == '') {
        return '';
    }

    var re = arg + '';
    if (re.length < 2) {
        re = '0' + re;
    }

    return re;
}

function initialSiteNav() {
    var list = [];
    for (var i = 0; i < 7; i++) {
        var date = new Date();
        date = addDate(date, i);
        var text = date;
        var value = text;
        list.push({'text': text, 'value': value});
    }
    var nhtml = '';
    for (var i = 0; i < list.length; i++) {
        var d = list[i];
        nhtml += '<li><a href="javascript:void(0);">' + d.text + '</a></li>';
    }
    //var nhtml = '<li><a href="javascript:void(0);">今天1月16日</a></li> <li><a href="javascript:void(0);" class="now">明天1月17日</a></li><li><a href="javascript:void(0);">周一1月18日</a></li><li><a href="javascript:void(0);">周一1月18日</a></li><li><a href="javascript:void(0);">周一1月18日</a></li>';
    var $nav = $('.reserve_title ul');

    $nav.append(nhtml);

    $nav.on("click", "li a", function () {
        if ($(this).hasClass("now")) {
            return;
        }
        $('li a', $nav).removeClass('now');
        $(this).addClass('now');
        var value = $(this).html();
        console.log(value);
        initialSiteList(value);
    });
    $('li a:first', $nav).click();
}
