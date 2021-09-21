<#import "/spring.ftl" as spring/>

<#macro html title>
    <html>
       <head>
          <title>${title}</title>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
          <link rel="stylesheet" href="<@spring.url '/static/css/main.css'/>" type="text/css" />
          <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"/>"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"/>"></script>
          <script src="<@spring.url '/static/js/main.js'/>"/>"></script>
       </head>
       <body>
          <div class="ui raised very padded container segment">
             <#nested>
          </div>
       </body>
    </html>
</#macro>

<#macro wordSearchInput word="">
    <form action="<@spring.url '/search-translation/'/>">
       <div class="ui pointing below label">
          Please specify a word or phrase for search of translation, context and pronunciation
       </div>
       <div class="ui fluid action input">
          <input type="text" placeholder="Search..." name="word" value="${word}" required>
          <button class="ui button">Search</button>
       </div>
    </form>
</#macro>

<#macro usefulLinksSection translationResult>
    <div class="ui card fluid">
       <div class="content">
          <div class="header">Useful links</div>
          <div class="ui middle aligned divided list">
             <div class="item">
                <img class="ui avatar image" src="/static/images/reverso-context.png">
                <div class="content">
                   <a class="header" target="_blank" href="${translationResult.contextUri}">ReversoContext:</a>
                   <div class="description">${translationResult.originalWord}</div>
                </div>
             </div>
             <div class="item">
                <img class="ui avatar image" src="/static/images/google-translate.png">
                <div class="content">
                   <a class="header" target="_blank" href="${translationResult.translationUri}">Google translate:</a>
                   <div class="description">${translationResult.originalWord}</div>
                </div>
             </div>
          </div>
       </div>
       <div class="extra content">
          <audio controls src="${translationResult.pronunciationUri}"></audio>
       </div>
    </div>
</#macro>

<#macro generateWordContextForm translationResult>
    <form class="ui form" action="<@spring.url '/generate-anki-flashcards/'/>" method="POST">
      <div class="field">
        <label>Original word</label>
        <input type="text" name="originalWord" placeholder="Original word" value="${translationResult.originalWord}">
      </div>
      <div class="field">
        <label>Translation</label>
        <textarea rows="2" name="translation" placeholder="Translation"></textarea>
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
        <input type="text" name="pronunciationUri" placeholder="Pronunciation uri" value="${translationResult.pronunciationUri}">
      </div>
      <button class="ui primary right floated button" type="submit">Generate flashcard</button>
    </form>
</#macro>

<#macro mainPageButton customClasses="">
    <form action="<@spring.url '/'/>">
        <button class="ui button ${customClasses}">Generate cards for another word.</button>
    </form>
</#macro>

<#macro exportCardsButton wordContextHolder successMsgId successDescriptionId errorMsgId customClasses="" >
    <button class="ui button ${customClasses}"
        onClick="submitExportCardForm('<@spring.url '/export-anki-flashcards'/>', '${successMsgId}', '${successDescriptionId}', '${errorMsgId}',
        {
            'originalWord': '${wordContextHolder.originalWord}',
            'originalContext': '${wordContextHolder.originalContext}',
            'translatedWords': [<#list wordContextHolder.translatedWords as translatedWord>'${translatedWord}',</#list>],
            'translatedContext': '${wordContextHolder.translatedContext}',
            'pronunciationFileName': '${wordContextHolder.pronunciationFileName}'
        })">
        Export cards to file.</button>
</#macro>

<#macro message type id text description="" descriptionId="">
    <div id="${id}" class="ui ${type} message hidden">
      <i class="close icon" onClick="hideMessage('${id}')"></i>
      <div class="header">
        ${text}
      </div>
      <#if (description != "")>
         <p id="${descriptionId}">${description}</p>
      </#if>
    </div>
</#macro>

