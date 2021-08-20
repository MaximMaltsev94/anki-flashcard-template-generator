<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>

<@commonTags.html title="Akni flashcard template generator">
    <div class="ui pointing below label">
        Please specify a word or phrase for search of translation, context and pronunciation
    </div>
    <div class="ui fluid action input">
        <input type="text" placeholder="Search...">
        <div class="ui button">Search</div>
    </div>
</@commonTags.html>