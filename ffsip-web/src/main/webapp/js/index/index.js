$(function () {

    initialIndexBanner();
    iniNewsList();
})

function initialIndexBanner() {


    var _basePath = _pathTotal + 'activities.do';
    var _getSInfoJson = {};
    //$.frontEngineAjax.executeAjax(_basePath,"get",_getSInfoJson,function(getSInfoResult){
    /* common_ajax({
     url: _basePath, success: function (getSInfoResult) {
     console.log(getSInfoResult);


     }
     })*/

    $(".swiper-container").swiper({
        autoplay: 5000,
        autoplayDisableOnInteraction: false,
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: true,
        autoHeight: true
    });

};

function iniNewsList() {

    renderActivi(0, '.index_news');

}


function renderActivi(typeactivity, ob) {

    var _basePath = _pathTotal + 'activities.do?type=' + typeactivity;
    var _htmlList = '';
    common_ajax({
        url: _basePath, success: function (data) {

            if (typeactivity === 0) {
                for (var i = 0; i < data.length; i++) {
                    var _$item = data[i];
                    var _itmeImage = _commonbasePath + _$item.image || '/images/index_news.jpg';	//图片
                    var _url = _$item.url || '';						//路径
                    var _stratTime = new Date(_$item.startDateTime).format('yyyy-MM-dd') || new Date().getTime();	//开始时间
                    var _title = _$item.name || '';				//标题
                    var _describe = _$item.introduction || '';	//描述详情
                    _htmlList = _htmlList + '<dl><dt><a href="' + _url + '"><img src="' + _itmeImage + '" alt=""/></a></dt><dd><a href="' + _url + '"><h3>' + _title + '</h3><span class="news_time">' + _stratTime + '</span><p>' + _describe + '</p></a></dd></dl>';
                }

            } else if (typeactivity === 1) {

            }

            $(ob).html(_htmlList);

        }
    })


}
