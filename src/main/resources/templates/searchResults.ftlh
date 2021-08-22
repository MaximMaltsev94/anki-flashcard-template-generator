<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>


<@commonTags.html title="Word search results">
    <@commonTags.wordSearchInput word="${translationResult.originalWord}"/>

    <div class="ui divider"></div>

    <@commonTags.usefulLinksSection translationResult=translationResult/>

    <div class="ui divider"></div>

    <@commonTags.generateWordContextForm translationResult=translationResult/>

</@commonTags.html>