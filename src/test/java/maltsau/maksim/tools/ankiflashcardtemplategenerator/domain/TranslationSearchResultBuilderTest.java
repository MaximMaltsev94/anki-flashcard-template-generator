package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

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

        assertThat(translationSearchResult, is(notNullValue()));
        assertThat(translationSearchResult.getOriginalWord(), is(equalTo(ORIGINAL_WORD)));
        assertThat(translationSearchResult.getContextUri(), is(equalTo(CONTEXT_URI)));
        assertThat(translationSearchResult.getTranslationUri(), is(equalTo(TRANSLATION_URI)));
        assertThat(translationSearchResult.getPronunciationUri(), is(equalTo(PRONUNCIATION_URI)));
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

        assertThat(translationSearchResult, is(notNullValue()));
        assertThat(translationSearchResult.getOriginalWord(), is(nullValue()));
        assertThat(translationSearchResult.getContextUri(), is(nullValue()));
        assertThat(translationSearchResult.getTranslationUri(), is(nullValue()));
        assertThat(translationSearchResult.getPronunciationUri(), is(nullValue()));
    }
}