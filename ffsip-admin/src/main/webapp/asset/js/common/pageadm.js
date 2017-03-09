
/**
@category : 分页提交表单
@param _naviValue : 第几页的值
@see : 
*/
function naviCommonSubmit(_naviValue) {
	var flag = /^[1-9]\d*$/.test(_naviValue);//验证“正整数”
	if(flag){
		var findPageCount = document.getElementById("find-page-count").value;
		findPageCount = Number(findPageCount);
		_naviValue = Number(_naviValue);
		if(_naviValue > findPageCount ){
			alert("页数太大了");
		}else{
			document.getElementById("find-page-index").value = _naviValue;
			document.getElementById("find-page-orderby-button").click();
		}
	}else{
		alert("页数错误");
	}
}

/**
@category : 设置分页大小提交表单
@param _pagesizeValue : 分页大小
@see : 
*/
function pagesizeCommonSubmit(_pagesizeValue) {
	var flag = /^[1-9]\d*$/.test(_pagesizeValue);//验证“正整数”
	if(flag){
		document.getElementById("find-page-size").value = _pagesizeValue;
		document.getElementById("find-page-orderby-button").click();
	}else{
		alert("页数大小错误");
	}
}
