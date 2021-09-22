package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class WordContextHolderBuilderTest {

    private static final String ORIGINAL_WORD = "original word";
    private static final String ORIGINAL_CONTEXT = "original context";
    private static final List<String> TRANSLATED_WORDS = List.of("translated word 1", "translated word 2");
    private static final String TRANSLATED_CONTEXT = "translated context";
    private static final String PRONUNCIATION_FILE_NAME = "pronunciation file name";

    @Test
    public void testBuilderSetAllProperties() {
        //given
        WordContextHolder.Builder builder = WordContextHolder.builder();

        //when
        WordContextHolder wordContextHolder = builder
                .withOriginalWord(ORIGINAL_WORD)
                .withOriginalContext(ORIGINAL_CONTEXT)
                .withTranslatedWords(TRANSLATED_WORDS)
                .withTranslatedContext(TRANSLATED_CONTEXT)
                .withPronunciationFileName(PRONUNCIATION_FILE_NAME)
                .build();

        //then

        assertThat(wordContextHolder, is(notNullValue()));
        assertThat(wordContextHolder.getOriginalWord(), is(equalTo(ORIGINAL_WORD)));
        assertThat(wordContextHolder.getOriginalContext(), is(equalTo(ORIGINAL_CONTEXT)));
        assertThat(wordContextHolder.getTranslatedWords(), is(equalTo(TRANSLATED_WORDS)));
        assertThat(wordContextHolder.getTranslatedContext(), is(equalTo(TRANSLATED_CONTEXT)));
        assertThat(wordContextHolder.getPronunciationFileName(), is(equalTo(PRONUNCIATION_FILE_NAME)));
    }

    @Test
    public void testBuilderNotFailOnNullValues() {
        //given
        WordContextHolder.Builder builder = WordContextHolder.builder();

        //when
        WordContextHolder wordContextHolder = builder
                .withOriginalWord(null)
                .withOriginalContext(null)
                .withTranslatedWords(null)
                .withTranslatedContext(null)
                .withPronunciationFileName(null)
                .build();

        //then
        assertThat(wordContextHolder, is(notNullValue()));
        assertThat(wordContextHolder.getOriginalWord(), is(nullValue()));
        assertThat(wordContextHolder.getOriginalContext(), is(nullValue()));
        assertThat(wordContextHolder.getTranslatedWords(), is(nullValue()));
        assertThat(wordContextHolder.getTranslatedContext(), is(nullValue()));
        assertThat(wordContextHolder.getPronunciationFileName(), is(nullValue()));
    }
}