function gen_action(item) {
	var id = item.id;
	var objAction = {
		edit: [
			{
				label: '编辑',
				href: "javascript: iframeFullPage('"+ rootPath +"/Company/toForm.do?id="+ id +"')"
			}
		],

		deletes: [
			{
				label: '删除',
				href: rootPath + '/Company/delete.do?id=' + id,
				class:'action_delete'
			}
		],

	};
	
	return objAction;
}

$(document).ready(function(){
//页面初始化预处理


});