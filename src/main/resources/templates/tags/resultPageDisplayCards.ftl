<#ftl output_format="HTML" auto_esc=true>
<#import "/cards/cardTemplates.ftl" as cardTemplates/>

<#macro basicDisplayCard wordsList context="" soundFileName="" withSound=false>
<pre class="ui secondary inverted full-height segment">
<#autoesc>
<@cardTemplates.basicTemplate
                wordsList=wordsList
                context=context
                soundFileName=soundFileName
                withSound=withSound/>
</#autoesc>
</pre>
</#macro>
