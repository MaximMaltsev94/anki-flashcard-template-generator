package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.LinkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TranslationSearchService}.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@Service
public class TranslationSearchServiceImpl implements TranslationSearchService {

    private final LinkGenerator reversoContextLinkGenerator;

    private final LinkGenerator googleTranslateLinkGenerator;

    private final LinkGenerator pronunciationLinkGenerator;

    @Autowired
    public TranslationSearchServiceImpl(LinkGenerator reversoContextLinkGenerator,
                                        LinkGenerator googleTranslateLinkGenerator,
                                        LinkGenerator pronunciationLinkGenerator) {
        this.reversoContextLinkGenerator = reversoContextLinkGenerator;
        this.googleTranslateLinkGenerator = googleTranslateLinkGenerator;
        this.pronunciationLinkGenerator = pronunciationLinkGenerator;
    }

    @Override
    public TranslationSearchResult searchTranslation(String word) {
        String lowercaseWord = word.toLowerCase();
        return TranslationSearchResult.builder()
                .withOriginalWord(lowercaseWord)
                .withTranslationUri(googleTranslateLinkGenerator.generateLink(lowercaseWord))
                .withContextUri(reversoContextLinkGenerator.generateLink(lowercaseWord))
                .withPronunciationUri(pronunciationLinkGenerator.generateLink(lowercaseWord))
                .build();
    }
}
