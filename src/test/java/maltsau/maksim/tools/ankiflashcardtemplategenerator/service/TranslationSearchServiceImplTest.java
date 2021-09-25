package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;


import maltsau.maksim.tools.ankiflashcardtemplategenerator.BaseTestNGMockSetup;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.LinkGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TranslationSearchServiceImplTest extends BaseTestNGMockSetup {

    private static final String WORD_TO_SEARCH = "word to search";
    private static final String UPPERCASE_WORD = "Word With Upper Case";
    private static final String LOWERCASE_WORD = UPPERCASE_WORD.toLowerCase();
    private static final String GOOGLE_PREFIX = "google prefix_";
    private static final String REVERSO_PREFIX = "reverso prefix_";
    private static final String PRONUNCIATION_PREFIX = "pronunciation prefix_";

    @Mock
    private LinkGenerator reversoContextLinkGenerator;

    @Mock
    private LinkGenerator googleTranslateLinkGenerator;

    @Mock
    private LinkGenerator pronunciationLinkGenerator;

    @InjectMocks
    private TranslationSearchServiceImpl translationSearchService;

    @BeforeMethod
    public void setUp() {
        Mockito.reset(reversoContextLinkGenerator, googleTranslateLinkGenerator, pronunciationLinkGenerator);
        translationSearchService = new TranslationSearchServiceImpl(reversoContextLinkGenerator,
                googleTranslateLinkGenerator, pronunciationLinkGenerator);
    }

    @Test
    public void testSearchTranslation() {
        //given
        when(googleTranslateLinkGenerator.generateLink(WORD_TO_SEARCH)).thenReturn(GOOGLE_PREFIX + WORD_TO_SEARCH);
        when(reversoContextLinkGenerator.generateLink(WORD_TO_SEARCH)).thenReturn(REVERSO_PREFIX + WORD_TO_SEARCH);
        when(pronunciationLinkGenerator.generateLink(WORD_TO_SEARCH)).thenReturn(PRONUNCIATION_PREFIX + WORD_TO_SEARCH);

        //when
        TranslationSearchResult translationSearchResult = translationSearchService.searchTranslation(WORD_TO_SEARCH);

        //then
        assertThat(translationSearchResult, is(notNullValue()));
        assertThat(translationSearchResult.getOriginalWord(), is(equalTo(WORD_TO_SEARCH)));
        assertThat(translationSearchResult.getTranslationUri(), is(equalTo(GOOGLE_PREFIX + WORD_TO_SEARCH)));
        assertThat(translationSearchResult.getContextUri(), is(equalTo(REVERSO_PREFIX + WORD_TO_SEARCH)));
        assertThat(translationSearchResult.getPronunciationUri(), is(equalTo(PRONUNCIATION_PREFIX + WORD_TO_SEARCH)));

        verify(googleTranslateLinkGenerator).generateLink(WORD_TO_SEARCH);
        verify(reversoContextLinkGenerator).generateLink(WORD_TO_SEARCH);
        verify(pronunciationLinkGenerator).generateLink(WORD_TO_SEARCH);
        verifyNoMoreInteractions(googleTranslateLinkGenerator, reversoContextLinkGenerator, pronunciationLinkGenerator);
    }


    @Test
    public void testSearchTranslationConvertToLowerCase() {
        //given
        when(googleTranslateLinkGenerator.generateLink(LOWERCASE_WORD)).thenReturn(GOOGLE_PREFIX + LOWERCASE_WORD);
        when(reversoContextLinkGenerator.generateLink(LOWERCASE_WORD)).thenReturn(REVERSO_PREFIX + LOWERCASE_WORD);
        when(pronunciationLinkGenerator.generateLink(LOWERCASE_WORD)).thenReturn(PRONUNCIATION_PREFIX + LOWERCASE_WORD);

        //when
        TranslationSearchResult translationSearchResult = translationSearchService.searchTranslation(UPPERCASE_WORD);

        //then
        assertThat(translationSearchResult, is(notNullValue()));
        assertThat(translationSearchResult.getOriginalWord(), is(equalTo(LOWERCASE_WORD)));
        assertThat(translationSearchResult.getTranslationUri(), is(equalTo(GOOGLE_PREFIX + LOWERCASE_WORD)));
        assertThat(translationSearchResult.getContextUri(), is(equalTo(REVERSO_PREFIX + LOWERCASE_WORD)));
        assertThat(translationSearchResult.getPronunciationUri(), is(equalTo(PRONUNCIATION_PREFIX + LOWERCASE_WORD)));

        verify(googleTranslateLinkGenerator).generateLink(LOWERCASE_WORD);
        verify(reversoContextLinkGenerator).generateLink(LOWERCASE_WORD);
        verify(pronunciationLinkGenerator).generateLink(LOWERCASE_WORD);
        verifyNoMoreInteractions(googleTranslateLinkGenerator, reversoContextLinkGenerator, pronunciationLinkGenerator);
    }

    @Test
    public void testSearchTranslationNotFailOnNullInput() {
        //given

        //when
        TranslationSearchResult translationSearchResult = translationSearchService.searchTranslation(null);

        //then
        assertThat(translationSearchResult, is(notNullValue()));
        assertThat(translationSearchResult.getOriginalWord(), is(nullValue()));
        assertThat(translationSearchResult.getTranslationUri(), is(nullValue()));
        assertThat(translationSearchResult.getContextUri(), is(nullValue()));
        assertThat(translationSearchResult.getPronunciationUri(), is(nullValue()));

        verifyNoMoreInteractions(googleTranslateLinkGenerator, reversoContextLinkGenerator, pronunciationLinkGenerator);
    }
}