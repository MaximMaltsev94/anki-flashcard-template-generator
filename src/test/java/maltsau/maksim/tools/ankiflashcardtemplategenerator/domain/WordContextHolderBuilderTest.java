package maltsau.maksim.tools.ankiflashcardtemplategenerator.domain;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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

        assertNotNull(wordContextHolder);
        assertEquals(wordContextHolder.getOriginalWord(), ORIGINAL_WORD);
        assertEquals(wordContextHolder.getOriginalContext(), ORIGINAL_CONTEXT);
        assertEquals(wordContextHolder.getTranslatedWords(), TRANSLATED_WORDS);
        assertEquals(wordContextHolder.getTranslatedContext(), TRANSLATED_CONTEXT);
        assertEquals(wordContextHolder.getPronunciationFileName(), PRONUNCIATION_FILE_NAME);
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
        assertNotNull(wordContextHolder);
        assertNull(wordContextHolder.getOriginalWord());
        assertNull(wordContextHolder.getOriginalContext());
        assertNull(wordContextHolder.getTranslatedWords());
        assertNull(wordContextHolder.getTranslatedContext());
        assertNull(wordContextHolder.getPronunciationFileName());
    }
}