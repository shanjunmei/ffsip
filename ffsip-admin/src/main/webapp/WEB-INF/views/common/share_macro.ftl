
<#macro JSONArray dataDict>
[<#list data_dict(dataDict) as element>{"value":"${element.valueStr}","label":"${element.nameStr}"}<#if element_has_next>,</#if></#list>]
</#macro>

<#macro JSONObject dataDict>
{<#list data_dict(dataDict) as element>"${element.valueStr}":"${element.nameStr}"<#if element_has_next>,</#if></#list>}
</#macro>

<#macro valueToLabel dataDict myValue><#list data_dict(dataDict) as element><#if (element.valueStr == myValue) >${element.nameStr}</#if></#list></#macro>

<#macro labelToValue dataDict myLabel><#list data_dict(dataDict) as element><#if (element.nameStr == myLabel) >${element.valueStr}</#if></#list></#macro>

<#macro appVer>v=${Application.jsVersion?default('1.0.0')}</#macro>