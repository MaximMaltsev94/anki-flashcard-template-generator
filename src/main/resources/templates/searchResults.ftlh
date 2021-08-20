<#import "/spring.ftl" as spring/>
<#import "/tags/common.ftl" as commonTags/>


<@commonTags.html title="Word search results">
    <@commonTags.wordSearchInput word="${translationResult.originalWord}"/>

    <div class="ui divider"></div>

    <@commonTags.usefulLinksSection translationResult=translationResult/>

    <div class="ui divider"></div>

    <form class="ui form">
      <div class="field">
        <label>Original word</label>
        <input type="text" name="originalWord" placeholder="Original word" value="${translationResult.originalWord}">
      </div>
      <div class="field">
        <label>Translation</label>
        <input type="text" name="translation" placeholder="Translation">
      </div>
      <div class="field">
        <label>Original context</label>
        <textarea rows="2" name="originalContext" placeholder="Original context"></textarea>
      </div>
      <div class="field">
        <label>Translated context</label>
        <textarea rows="2" name="translatedContext" placeholder="Original context"></textarea>
      </div>
      <div class="field">
        <label>Pronunciation uri</label>
        <input type="text" name="pronunciationUri" placeholder="Pronunciation uri">
      </div>
      <button class="ui primary right floated button" type="submit">Generate flashcard</button>
    </form>
</@commonTags.html>