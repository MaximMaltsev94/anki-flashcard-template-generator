package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class TranslationSearchResultBuilderTest {

    private static final String ORIGINAL_WORD = "original word";
    private static final String CONTEXT_URI = "context uri";
    private static final String TRANSLATION_URI = "translation uri";
    private static final String PRONUNCIATION_URI = "pronunciation uri";

    @Test
    public void testBuilderSetAllProperties() {
        //given
        TranslationSearchResult.Builder builder = TranslationSearchResult.builder();

        //when
        TranslationSearchResult translationSearchResult = builder
                .withOriginalWord(ORIGINAL_WORD)
                .withContextUri(CONTEXT_URI)
                .withTranslationUri(TRANSLATION_URI)
                .withPronunciationUri(PRONUNCIATION_URI)
                .build();

        //then

        assertNotNull(translationSearchResult);
        assertEquals(translationSearchResult.getOriginalWord(), ORIGINAL_WORD);
        assertEquals(translationSearchResult.getContextUri(), CONTEXT_URI);
        assertEquals(translationSearchResult.getTranslationUri(), TRANSLATION_URI);
        assertEquals(translationSearchResult.getPronunciationUri(), PRONUNCIATION_URI);
    }

    @Test
    public void testBuilderNotFailOnNullValues() {
        //given
        TranslationSearchResult.Builder builder = TranslationSearchResult.builder();

        //when
        TranslationSearchResult translationSearchResult = builder
                .withOriginalWord(null)
                .withContextUri(null)
                .withTranslationUri(null)
                .withPronunciationUri(null)
                .build();

        //then

        assertNotNull(translationSearchResult);
        assertNull(translationSearchResult.getOriginalWord());
        assertNull(translationSearchResult.getContextUri());
        assertNull(translationSearchResult.getTranslationUri());
        assertNull(translationSearchResult.getPronunciationUri());
    }
}