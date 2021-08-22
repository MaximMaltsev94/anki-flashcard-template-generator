<#ftl output_format="HTML" auto_esc=true>

<#macro basicTemplate wordsList context="" soundFileName="" withSound=false>
    <pre class="ui secondary inverted segment">
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
</pre>
</#macro>

<#macro basicBackTemplate word context>
</#macro>