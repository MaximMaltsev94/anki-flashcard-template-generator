package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.LinkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Implementation of {@link TranslationSearchService}.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@Service
public class TranslationSearchServiceImpl implements TranslationSearchService {

    private static final TranslationSearchResult DEFAULT_EMPTY_SEARCH_RESULT =
            TranslationSearchResult.builder().build();

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
        if (!StringUtils.hasText(word)) {
            return DEFAULT_EMPTY_SEARCH_RESULT;
        }

        String lowercaseWord = word.toLowerCase();
        return TranslationSearchResult.builder()
                .withOriginalWord(lowercaseWord)
                .withTranslationUri(googleTranslateLinkGenerator.generateLink(lowercaseWord))
                .withContextUri(reversoContextLinkGenerator.generateLink(lowercaseWord))
                .withPronunciationUri(pronunciationLinkGenerator.generateLink(lowercaseWord))
                .build();
    }
}
