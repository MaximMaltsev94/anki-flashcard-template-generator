package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;

public interface WordContextService {
    WordContextHolder generateContext(String originalWord,
                                      String originalContext,
                                      String translatedWords,
                                      String translatedContext,
                                      String pronunciationUri);
}
