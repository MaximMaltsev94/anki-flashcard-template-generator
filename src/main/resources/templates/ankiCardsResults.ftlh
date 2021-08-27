<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>
<#import "/tags/cardTemplates.ftl" as cardTemplates/>

<@commonTags.html title="Akni flashcard results">
    <div class="ui two column grid">
      <div class="row">
        <div class="column">
            <h2 class="ui header">
                Type input card type
                <div class="sub header">For card type, which require keyboard input for word.</div>
            </h2>
        </div>
        <div class="column">
            <h2 class="ui header">
                Basic card
                <div class="sub header">Both reversed and original basic card type can be created based on it.</div>
            </h2>
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

      <div class="row">
        <div class="column">
        </div>
        <div class="column">
            <@commonTags.mainPageButton customClasses="primary right floated"/>
        </div>
      </div>
    </div>

</@commonTags.html>