package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;

/**
 * Service for searching of word translation.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public interface TranslationSearchService {

    /**
     * Method for searching translation for specified word.
     * @param word for searching of translation
     * @return {@link TranslationSearchResult} object with results of translation search.
     */
    TranslationSearchResult searchTranslation(String word);
}
