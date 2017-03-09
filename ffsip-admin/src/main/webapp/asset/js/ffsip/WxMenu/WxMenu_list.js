function gen_action(item) {
    var id = item.id;
    var objAction = {
        edit: [
            {
                label: '编辑',
                href: "javascript: iframeFullPage('" + rootPath + "/WxMenu/toForm.do?id=" + id + "')"
            }
        ],

        delete: [
            {
                label: '删除',
                href: rootPath + '/WxMenu/delete.do?id=' + id
            }
        ]
    };
    console.log(rootPath);
    return objAction;
}


$(document).ready(function () {
//页面初始化预处理


});

function publish() {
    ffzx.ui([
        'dialog' // 浮动弹窗
    ], function () {
        var url = rootPath + "/WxMenu/publishWxMenu.do";
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            async: false,
            cache: false,
            success: function (result) {
                if (result.code == 0) {
                    dialog({
                        quickClose: true,
                        content: '发布成功！'
                    }).show();

                } else {
                    dialog({
                        quickClose: true,
                        content: result.msg
                    }).show();
                }
            }
        });
    });


}