function gen_action(item) {
	var id = item.id;
	var objAction = {
		edit: [
			{
				label: '编辑',
				href: "javascript: iframeFullPage('"+ rootPath +"/User/toForm.do?id="+ id +"')"
			}
		],

		delete: [
			{
				label: '删除',
				href: rootPath + '/User/delete.do?id='+id
			}
		]
	};
	console.log(rootPath);
	return objAction;
}

/*

function isdelete(id,name) {
    $.frontEngineDialog.executeDialog(
            'isdelete', 
            '删除', 
            '是否确定删除“'+name+'”？', 
            "250px", 
            "35px", 
            function() {
        $.ajax({
            url : rootPath + "/User/delete.do",
            data : {
                id : id
            },// 给服务器的参数
            type : "POST",
            dataType : "json",
            async : false,
            cache : false,
            success : function(result) {
                if (result.status == 'success' || result.code == 0) {
                    dialog({
                        quickClose : true,
                        content : '操作成功！'
                    }).show();
                    setTimeout('window.location.href="' + rootPath+ '/User/toList.do"', 1000);
                } else {
                    dialog({
                        quickClose : true,
                        content : result.msg
                    }).show();
                }
            }
        });
    });
}
*/





$(document).ready(function(){
//页面初始化预处理


});