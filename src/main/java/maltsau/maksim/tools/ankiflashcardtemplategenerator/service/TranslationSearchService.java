package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;

public interface TranslationSearchService {
    TranslationSearchResult searchTranslation(String word);
}
