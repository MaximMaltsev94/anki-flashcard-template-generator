<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>
<#import "/tags/cardTemplates.ftl" as cardTemplates/>

<@commonTags.html title="Akni flashcard results">
    <div class="ui two column divided grid">
      <div class="row">
        <div class="column">
            <h2>Type input card type</h2>
        </div>
        <div class="column">
            <h2>Basic card (both reversed and original can be created based on it)</h2>
        </div>
      </div>

      <div class="row">
        <div class="column">
            <@cardTemplates.basicTemplate
                wordsList=wordContextHolder.translatedWords
                context=wordContextHolder.translatedContext/>
        </div>
        <div class="column">
            <@cardTemplates.basicTemplate
                wordsList=[wordContextHolder.originalWord]
                context=wordContextHolder.originalContext
                withSound=true
                soundFileName=wordContextHolder.pronunciationFileName/>
        </div>
      </div>

      <div class="row">
        <div class="column">
            <@cardTemplates.basicTemplate
                wordsList=[wordContextHolder.originalWord]/>
        </div>
        <div class="column">
            <@cardTemplates.basicTemplate
                wordsList=wordContextHolder.translatedWords
                context=wordContextHolder.translatedContext/>
        </div>
      </div>
    </div>

</@commonTags.html>