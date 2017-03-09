
<#-- 前台的分页封装宏 -->
<#macro my_page pageObj>
	
	<#if pageObj.totalCount==0 >
		
	<#else>
		<div class="dataTables_paginate paging_simple_numbers  col-md-12" id="example2_paginate">
        <ul class="pagination">
        
		<#assign p_count=pageObj.pageCount>
		<#assign p_index=pageObj.pageIndex>
		
		<#-- 是否显示"首页" -->
		<#if pageObj.hasPrevious>
	    	<#assign p_tmp=p_index-1>
	    	<li class="paginate_button previous" id="example1_previous"><a href="javascript:void(0);" onclick="naviCommonSubmit('1')" aria-controls="example2" data-dt-idx="0" tabindex="0">首页</a></li>
	    <#else>
	    	<li class="paginate_button previous disabled" id="example1_previous"><a href="javascript:void(0);" aria-controls="example2" data-dt-idx="0" tabindex="0">首页</a></li>
	    </#if>
				
		<#-- 是否显示"上一页" -->
		<#if pageObj.hasPrevious>
	    	<#assign p_tmp=p_index-1>
	    	<li class="paginate_button previous" id="example2_previous"><a href="javascript:void(0);" onclick="naviCommonSubmit('${p_tmp}')" aria-controls="example2" data-dt-idx="0" tabindex="0"><< 上一页</a></li>
	    <#else>
	    	<li class="paginate_button previous disabled" id="example2_previous"><a href="javascript:void(0);" aria-controls="example2" data-dt-idx="0" tabindex="0"><< 上一页</a></li>
	    </#if>
	    
	    <#-- 如果前面页数过多,显示"..." -->
	    <#if (p_index>5) >
	    	<#assign p_prevPages=p_index-5>
	        <#assign p_start=p_index-4>
	        <li class="paginate_button "><a href="javascript:void(0);" onclick="naviCommonSubmit('1')" aria-controls="example2" data-dt-idx="2" tabindex="0">1</a></li>
	        <#if (p_start != 2) >
	        	<li class="paginate_button more_page">....</li>
	        </#if>
	    <#else>
	    	<#assign p_start=1>
	    </#if>
	    
	    <#-- 显示当前页附近的页 -->
	   	<#assign p_end=p_index+4>
	    <#if (p_end>p_count) >
	    	<#assign p_end=p_count>
	    </#if>
	    <#list p_start..p_end as i>
	    	<#if i==p_index>
	        	<li class="paginate_button active"><a href="javascript:void(0);" aria-controls="example2" data-dt-idx="1" tabindex="0">${i}</a></li>
	        <#else>
				<li class="paginate_button "><a href="javascript:void(0);" onclick="naviCommonSubmit('${i}')" aria-controls="example2" data-dt-idx="6" tabindex="0">${i}</a></li>
	        </#if>
	    </#list>
	    
	    <#-- 如果后面页数过多,显示"..." -->
	    <#if (p_end<p_count) >
	       	<#assign p_nextPages=p_end+1>
	       	<#if (p_nextPages != p_count) >
	       		<li class="paginate_button more_page">....</li>
	       	</#if>
	       	<li class="paginate_button "><a href="javascript:void(0);" onclick="naviCommonSubmit('${p_count}')" aria-controls="example2" data-dt-idx="6" tabindex="0">${p_count}</a></li>
	    </#if>
	    
	    <#-- 显示"下一页" -->
	    <#if pageObj.hasNext >
	       	<#assign p_tmp=p_index+1>
	       	<li class="paginate_button next" id="example2_next"><a href="javascript:void(0);" onclick="naviCommonSubmit('${p_tmp}')" aria-controls="example2" data-dt-idx="7" tabindex="0">下一页 >></a></li>
	    <#else>
	       	<li class="paginate_button next disabled" id="example2_next"><a href="javascript:void(0);" aria-controls="example2" data-dt-idx="7" tabindex="0">下一页 >></a></li>
	    </#if>
	    
	    <#-- 显示"尾页" -->
	    <#if pageObj.hasNext >
	       	<#assign p_tmp=p_index+1>
	       	<li class="paginate_button next" id="example1_next"><a href="javascript:void(0);" onclick="naviCommonSubmit('${pageObj.pageCount}')" aria-controls="example2" data-dt-idx="7" tabindex="0">尾页</a></li>
	    <#else>
	       	<li class="paginate_button next disabled" id="example1_next"><a href="javascript:void(0);" aria-controls="example2" data-dt-idx="7" tabindex="0">尾页</a></li>
	    </#if>
	    
	    <li class="paginate_text">
	    	当前 ${pageObj.pageIndex}/${pageObj.pageCount} 页，共 ${pageObj.totalCount} 条，
	    	每页
	    	<select onchange="pagesizeCommonSubmit(this.value);" class="form-control input-sm">
	    		<option value="10" <#if (pageObj.pageSize == 10) >selected="selected"</#if>>10</option>
	    		<option value="20" <#if (pageObj.pageSize == 20) >selected="selected"</#if>>20</option>
	    		<option value="30" <#if (pageObj.pageSize == 30) >selected="selected"</#if>>30</option>
	    		<option value="50" <#if (pageObj.pageSize == 50) >selected="selected"</#if>>50</option>
	    		<option value="100" <#if (pageObj.pageSize == 100) >selected="selected"</#if>>100</option>
	    	</select>
	    	项
	    </li>
	    
	    </ul>
        </div>
	</#if>
	
</#macro>
