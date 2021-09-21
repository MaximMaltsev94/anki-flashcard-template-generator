<#ftl output_format="HTML" auto_esc=true>

<#macro basicTemplate wordsList context="" soundFileName="" withSound=false>
${'<div class="word">'}
  ${'<ul>'}
  <#list wordsList as word>
    ${'<li>'} ${word} <#if withSound>[sound:${soundFileName}]<#local withSound=false></#if>${'</li>'}
  </#list>
  ${'</ul>'}
${'</div>'}
<#if context!="">
${'<br>'}
${'<div class="context">'}
  ${context}
${'</div>'}
</#if>
</#macro>

<#macro basicTemplateNoEscape wordsList context="" soundFileName="" withSound=false>
<#noautoesc>
${'<div class="word">'}
  ${'<ul>'}
  <#list wordsList as word>
    ${'<li>'} ${word} <#if withSound>[sound:${soundFileName}]<#local withSound=false></#if>${'</li>'}
  </#list>
  ${'</ul>'}
${'</div>'}
<#if context!="">
${'<br>'}
${'<div class="context">'}
  ${context}
${'</div>'}
</#if>
</#noautoesc>
</#macro>