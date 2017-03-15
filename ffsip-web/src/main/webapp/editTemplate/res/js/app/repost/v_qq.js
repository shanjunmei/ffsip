window.onload = function() {
    $("iframe").each(function(){
        var width = $(".article-details").width();
        if(!width) width = $(this).parent().width();
        if(width > 500) width = 500;
        var height = width * 0.75;
        var srcUrl = $(this).attr('data-src');
        if(srcUrl == undefined) srcUrl = $(this).attr('src');
        if(/v.qq.com\/iframe/.test(srcUrl)) {
            srcUrl = srcUrl.replace(/preview\.html/, 'player.html').replace(/&width=[^&]*/, '').replace(/&height=[^&]*/, '');
            srcUrl += "&width="+width+"&height="+height;
            $(this).attr('width', width).attr('height', height).attr('src', srcUrl);
            if($(this).attr('style')) $(this).attr('style', $(this).attr('style').replace(/HEIGHT:[^;]*;?\s?/i, '').replace(/WIDTH:[^;]*;?\s?/i, ''));
        }
    })
};