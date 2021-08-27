package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;

/**
 * Service for generating word context object.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public interface WordContextService {

    /**
     * Method for generating of flashcard metadata
     * @param originalWord original word
     * @param originalContext an example of original word usage in context
     * @param translatedWords translated word
     * @param translatedContext an example of translated word usage in context
     * @param pronunciationUri uri with word pronunciation media file
     * @return aggregated {@link WordContextHolder} object with information for flashcard generation
     */
    WordContextHolder generateContext(String originalWord,
                                      String originalContext,
                                      String translatedWords,
                                      String translatedContext,
                                      String pronunciationUri);
}
