function gen_action(item) {
    var id = item.id;
    var objAction = {
        edit: [
            {
                label: '编辑',
                href: "javascript: iframeFullPage('" + rootPath + "/Contact/toForm.do?id=" + id + "')"
            }
        ],

        delete: [
            {
                label: '删除',
                href: rootPath + '/Contact/delete.do?id=' + id
            }
        ]
    };
    console.log(rootPath);
    return objAction;
}


$(document).ready(function () {
//页面初始化预处理


});

