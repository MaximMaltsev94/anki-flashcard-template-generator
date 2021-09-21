<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>
<#import "/tags/resultPageDisplayCards.ftl" as resultDisplayCards/>

<@commonTags.html title="Akni flashcard results">
    <div class="ui two column grid">
      <div class="one column row">
        <div class="column">
            <@commonTags.message 
              type="success"
              id="exportSuccessMessage"
              text="Anki cards successfully exported."
              description="You can find files in `export` folder in application directory."
              descriptionId="exportSuccessMessageDescription"/>
            
            <@commonTags.message 
              type="negative"
              id="exportErrorMessage"
              text="Error while exporting cards."
              description="Please check logs for error message."
              descriptionId="exportErrorMessageDescription"/>
        </div>
      </div>
      <div class="row">
        <div class="column">
            <h2 class="ui header">
                `Type in` card type
                <div class="sub header">For card type, which require keyboard input for word.</div>
            </h2>
        </div>
        <div class="column">
            <h2 class="ui header">
                `Basic` card type
                <div class="sub header">Both reversed and original basic card type can be created based on it.</div>
            </h2>
        </div>
      </div>

      <div class="row">
        <div class="column">
            <@resultDisplayCards.basicDisplayCard
                wordsList=wordContextHolder.translatedWords
                context=wordContextHolder.translatedContext/>
        </div>
        <div class="column">
            <@resultDisplayCards.basicDisplayCard
                wordsList=[wordContextHolder.originalWord]
                context=wordContextHolder.originalContext
                withSound=true
                soundFileName=wordContextHolder.pronunciationFileName/>
        </div>
      </div>

      <div class="row">
        <div class="column">
            <@resultDisplayCards.basicDisplayCard
                wordsList=[wordContextHolder.originalWord]/>
        </div>
        <div class="column">
            <@resultDisplayCards.basicDisplayCard
                wordsList=wordContextHolder.translatedWords
                context=wordContextHolder.translatedContext/>
        </div>
      </div>

      <div class="row">
        <div class="column">
        </div>
        <div class="column">
            <div class="ui buttons right floated">
                <@commonTags.exportCardsButton
                    wordContextHolder=wordContextHolder
                    successMsgId="exportSuccessMessage"
                    successDescriptionId="exportSuccessMessageDescription"
                    errorMsgId="exportErrorMessage"
                    customClasses="primary"
                    />
                <div class="or"></div>
                <@commonTags.mainPageButton customClasses="positive"/>
            </div>
        </div>
      </div>
    </div>

</@commonTags.html>