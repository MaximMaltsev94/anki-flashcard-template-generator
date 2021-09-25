package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Domain object for storing information for flashcard generation.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public class WordContextHolder {
    private String originalWord;
    private String originalContext;
    private List<String> translatedWords;
    private String translatedContext;
    private String pronunciationFileName;

    public WordContextHolder() {
    }

    public WordContextHolder(Builder init) {
        this.originalWord = init.prototype.originalWord;
        this.originalContext = init.prototype.originalContext;
        this.translatedWords = init.prototype.translatedWords;
        this.translatedContext = init.prototype.translatedContext;
        this.pronunciationFileName = init.prototype.pronunciationFileName;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getOriginalContext() {
        return originalContext;
    }

    public List<String> getTranslatedWords() {
        return Optional.ofNullable(translatedWords)
                .map(Collections::unmodifiableList)
                .orElse(null);
    }

    public String getTranslatedContext() {
        return translatedContext;
    }

    public String getPronunciationFileName() {
        return pronunciationFileName;
    }

    /**
     * Builder method.
     * @return Builder for {@link WordContextHolder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link WordContextHolder}.
     * Date: 22.08.2021
     *
     * @author Maksim Maltsau
     */
    public static class Builder {
        private final WordContextHolder prototype = new WordContextHolder();

        /**
         * Builds new object according to configured properties.
         * @return {@link WordContextHolder} object
         */
        public WordContextHolder build() {
            return new WordContextHolder(this);
        }

        public Builder withOriginalWord(String originalWord) {
            this.prototype.originalWord = originalWord;
            return this;
        }

        public Builder withOriginalContext(String originalContext) {
            this.prototype.originalContext = originalContext;
            return this;
        }

        public Builder withTranslatedWords(List<String> translatedWords) {
            this.prototype.translatedWords = translatedWords;
            return this;
        }

        public Builder withTranslatedContext(String translatedContext) {
            this.prototype.translatedContext = translatedContext;
            return this;
        }

        public Builder withPronunciationFileName(String pronunciationFileName) {
            this.prototype.pronunciationFileName = pronunciationFileName;
            return this;
        }
    }
}
