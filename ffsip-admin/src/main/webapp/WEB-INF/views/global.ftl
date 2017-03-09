<#-- Version Number -->
<#global ver = Application.jsVersion?default('1.0.0') />

<#-- Get system name -->
<#global sys = BasePath?lower_case?replace('/', '')?replace('-web', '') />

<#-- Get module name -->
<#global mod = springMacroRequestContext.getRequestUri()?lower_case?substring(1)?replace('/',' ')?replace('-web', '')?replace('.do','')?replace(' ', '-') />

<#import "common/json.ftl" as json>

<#include "common/share_macro.ftl" encoding="utf-8">

<#include "common/loadingffzx.ftl" encoding="utf-8"> 