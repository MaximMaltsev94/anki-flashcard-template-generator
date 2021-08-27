package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

/**
 * Domain object for storing results of search of word translation.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public class TranslationSearchResult {
    private String originalWord;
    private String contextUri;
    private String translationUri;
    private String pronunciationUri;

    public TranslationSearchResult() {
    }

    public TranslationSearchResult(Builder init) {
        this.originalWord = init.prototype.originalWord;
        this.contextUri = init.prototype.contextUri;
        this.translationUri = init.prototype.translationUri;
        this.pronunciationUri = init.prototype.pronunciationUri;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getContextUri() {
        return contextUri;
    }

    public String getTranslationUri() {
        return translationUri;
    }

    public String getPronunciationUri() {
        return pronunciationUri;
    }

    /**
     * Builder method.
     * @return Builder for {@link TranslationSearchResult}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link TranslationSearchResult}.
     * Date: 22.08.2021
     *
     * @author Maksim Maltsau
     */
    public static class Builder {
        private final TranslationSearchResult prototype = new TranslationSearchResult();

        /**
         * Builds new object according to configured properties.
         * @return {@link TranslationSearchResult} object
         */
        public TranslationSearchResult build() {
            return new TranslationSearchResult(this);
        }

        public Builder withOriginalWord(String originalWord) {
            this.prototype.originalWord = originalWord;
            return this;
        }

        public Builder withContextUri(String contextUri) {
            this.prototype.contextUri = contextUri;
            return this;
        }

        public Builder withTranslationUri(String translationUri) {
            this.prototype.translationUri = translationUri;
            return this;
        }

        public Builder withPronunciationUri(String pronunciationUri) {
            this.prototype.pronunciationUri = pronunciationUri;
            return this;
        }

    }

}
