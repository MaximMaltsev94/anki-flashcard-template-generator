<#import "/templates/cards/cardTemplates.ftl" as cardTemplates/>
<@cardTemplates.basicTemplateNoEscape
                wordsList=[wordContextHolder.originalWord]
                context=wordContextHolder.originalContext
                withSound=true
                soundFileName=wordContextHolder.pronunciationFileName/>
