package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.exception.WordContextServiceException;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage.FileStorageService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WordContextServiceImplTest {

    private static final String GOOGLE_COM = "https://www.google.com/";
    private static final String SAVED_FILE_NAME = "saved-file-name.mp3";

    private AutoCloseable mocksClosable;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private WordContextServiceImpl wordContextService;

    @BeforeMethod
    public void setUp() {
        mocksClosable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void afterClass() throws Exception {
        mocksClosable.close();
    }

    @Test
    public void testGenerateContext() {
        //given
        when(fileStorageService.saveFileAsMP3(any())).thenReturn(SAVED_FILE_NAME);

        //when
        WordContextHolder wordContextHolder = wordContextService.generateContext("original word",
                "original context", "translated word", "translated context",
                GOOGLE_COM);

        //then
        assertThat(wordContextHolder, is(notNullValue()));
        assertThat(wordContextHolder.getOriginalWord(), is(equalTo("original word")));
        assertThat(wordContextHolder.getOriginalContext(), is(equalTo("original context")));
        assertThat(wordContextHolder.getTranslatedWords(), is(equalTo(List.of("translated word"))));
        assertThat(wordContextHolder.getTranslatedContext(), is(equalTo("translated context")));
        assertThat(wordContextHolder.getPronunciationFileName(), is(equalTo(SAVED_FILE_NAME)));
    }

    @DataProvider(name = "forbiddenCharactersDataSet")
    private Object[][] forbiddenCharactersDataSet() {
        return new Object[][]{
                {"test", "test"},
                {"'test", "test"},
                {"test'", "test"},
                {"test'test'test", "testtesttest"},
        };
    }

    @Test(dataProvider = "forbiddenCharactersDataSet")
    public void testGenerateContextRemoveForbiddenCharacters(String inputText, String expectedProcessedText) {
        //given
        when(fileStorageService.saveFileAsMP3(any())).thenReturn(SAVED_FILE_NAME);

        //when
        WordContextHolder wordContextHolder = wordContextService.generateContext(inputText,
                inputText, inputText, inputText, GOOGLE_COM);

        //then
        assertThat(wordContextHolder, is(notNullValue()));
        assertThat(wordContextHolder.getOriginalWord(), is(equalTo(expectedProcessedText)));
        assertThat(wordContextHolder.getOriginalContext(), is(equalTo(expectedProcessedText)));
        assertThat(wordContextHolder.getTranslatedWords(), is(equalTo(List.of(expectedProcessedText))));
        assertThat(wordContextHolder.getTranslatedContext(), is(equalTo(expectedProcessedText)));
        assertThat(wordContextHolder.getPronunciationFileName(), is(equalTo(SAVED_FILE_NAME)));
    }

    @Test(expectedExceptions = WordContextServiceException.class, expectedExceptionsMessageRegExp = ".*malformed uri.*")
    public void testGenerateContextHandlesMalformedUriException() {
        //given

        //when
        wordContextService.generateContext("", "", "", "", "malformed uri");

        //then
    }

    @Test(expectedExceptions = WordContextServiceException.class, expectedExceptionsMessageRegExp = ".*my.test.custom.domain.com.*")
    public void testGenerateContextHandlesIOException() {
        //given

        //when
        wordContextService.generateContext("", "", "", "", "http://my.test.custom.domain.com/");

        //then
    }

    @DataProvider(name = "translatedWordsWithNewLineDataSet")
    private Object[][] translatedWordsWithNewLineDataSet() {
        return new Object[][]{
                {"word1", List.of("word1")},
                {"word1\nword2", List.of("word1", "word2")},
                {"word1\r\nword2", List.of("word1", "word2")}
        };
    }

    @Test(dataProvider = "translatedWordsWithNewLineDataSet")
    public void testGenerateContextSplitsByNewLine(String translatedWords, List<String> expectedParsedWords) {
        //given
        when(fileStorageService.saveFileAsMP3(any())).thenReturn(SAVED_FILE_NAME);

        //when
        WordContextHolder wordContextHolder = wordContextService.generateContext("", "", translatedWords, "", GOOGLE_COM);

        //then
        assertThat(wordContextHolder, is(notNullValue()));
        assertThat(wordContextHolder.getTranslatedWords(), is(equalTo(expectedParsedWords)));
    }

    @DataProvider(name = "generateContextNullArguments")
    private Object[][] generateContextNullArguments() {
        return new Object[][]{
                {null, "originalContext", "translatedWords", "translatedContext", GOOGLE_COM},
                {"originalWord", null, "translatedWords", "translatedContext", GOOGLE_COM},
                {"originalWord", "originalContext", null, "translatedContext", GOOGLE_COM},
                {"originalWord", "originalContext", "translatedWords", null, GOOGLE_COM},
                {"originalWord", "originalContext", "translatedWords", "translatedContext", null},
        };
    }

    @Test(dataProvider = "generateContextNullArguments")
    public void testGenerateContextNotFailOnNullArguments(String originalWord,
                                                          String originalContext,
                                                          String translatedWords,
                                                          String translatedContext,
                                                          String pronunciationUri) {
        //given
        when(fileStorageService.saveFileAsMP3(any())).thenReturn(SAVED_FILE_NAME);

        //when
        WordContextHolder wordContextHolder = wordContextService.generateContext(originalWord, originalContext,
                translatedWords, translatedContext, pronunciationUri);

        //then
        assertThat(wordContextHolder, is(notNullValue()));
    }


}