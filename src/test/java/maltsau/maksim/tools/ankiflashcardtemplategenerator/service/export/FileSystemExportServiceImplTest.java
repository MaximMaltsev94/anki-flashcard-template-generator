package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.export;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.BaseTestNGMockSetup;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.CardType;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage.FileStorageService;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.template.CardTemplateRenderService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class FileSystemExportServiceImplTest extends BaseTestNGMockSetup {

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private CardTemplateRenderService cardTemplateRenderService;

    @InjectMocks
    private FileSystemExportServiceImpl fileSystemExportService;

    @BeforeMethod
    public void setUp() {
        Mockito.reset(fileStorageService, cardTemplateRenderService);
    }

    @Test
    public void testExportAllCardTypes() {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().withOriginalWord("word").build();
        String basicExportString = "basicFrontTemplate | basicBackTemplate";
        String basicReversedExportString = "basicBackTemplate | basicFrontTemplate";
        String typeInExportString = "typeInFrontTemplate | typeInBackTemplate";

        when(fileStorageService.appendExportToFile(basicExportString, CardType.BASIC)).thenReturn("basic saved file name");
        when(cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder)).thenReturn("basicFrontTemplate");
        when(cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder)).thenReturn("basicBackTemplate");

        when(fileStorageService.appendExportToFile(basicReversedExportString, CardType.BASIC_REVERSED))
                .thenReturn("basic reversed saved file name");
        when(cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder)).thenReturn("basicFrontTemplate");
        when(cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder)).thenReturn("basicBackTemplate");

        when(fileStorageService.appendExportToFile(typeInExportString, CardType.TYPE_IN))
                .thenReturn("type in saved file name");
        when(cardTemplateRenderService.renderTypeInFrontTemplate(wordContextHolder)).thenReturn("typeInFrontTemplate");
        when(cardTemplateRenderService.renderTypeInBackTemplate(wordContextHolder)).thenReturn("typeInBackTemplate");

        //when
        Set<String> affectedFileNames = fileSystemExportService.exportAllCardTypes(wordContextHolder);

        //then
        assertThat(affectedFileNames, is(not(emptyCollectionOf(String.class))));
        assertThat(affectedFileNames, is(hasSize(3)));
        assertThat(affectedFileNames, is(containsInAnyOrder("basic saved file name",
                "basic reversed saved file name", "type in saved file name")));

        verify(fileStorageService).appendExportToFile(basicExportString, CardType.BASIC);
        verify(fileStorageService).appendExportToFile(basicReversedExportString, CardType.BASIC_REVERSED);
        verify(fileStorageService).appendExportToFile(typeInExportString, CardType.TYPE_IN);
        verify(cardTemplateRenderService, times(2)).renderBasicFrontTemplate(wordContextHolder);
        verify(cardTemplateRenderService, times(2)).renderBasicBackTemplate(wordContextHolder);
        verify(cardTemplateRenderService).renderTypeInFrontTemplate(wordContextHolder);
        verify(cardTemplateRenderService).renderTypeInBackTemplate(wordContextHolder);
        verifyNoMoreInteractions(fileStorageService, cardTemplateRenderService);
    }

    @Test
    public void testExportBasicCardType() {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().withOriginalWord("word").build();
        String expectedExportString = "basicFrontTemplate | basicBackTemplate";


        when(fileStorageService.appendExportToFile(expectedExportString, CardType.BASIC)).thenReturn("basic saved file name");
        when(cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder)).thenReturn("basicFrontTemplate");
        when(cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder)).thenReturn("basicBackTemplate");

        //when
        String affectedFileName = fileSystemExportService.exportBasicCardType(wordContextHolder);

        //then
        assertThat(affectedFileName, is(notNullValue()));
        assertThat(affectedFileName, is(equalTo("basic saved file name")));

        verify(fileStorageService).appendExportToFile(expectedExportString, CardType.BASIC);
        verify(cardTemplateRenderService).renderBasicFrontTemplate(wordContextHolder);
        verify(cardTemplateRenderService).renderBasicBackTemplate(wordContextHolder);
        verifyNoMoreInteractions(fileStorageService, cardTemplateRenderService);
    }

    @Test
    public void testExportBasicReversedCardType() {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().withOriginalWord("word").build();
        String expectedExportString = "basicBackTemplate | basicFrontTemplate";

        when(fileStorageService.appendExportToFile(expectedExportString, CardType.BASIC_REVERSED))
                .thenReturn("basic reversed saved file name");
        when(cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder)).thenReturn("basicFrontTemplate");
        when(cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder)).thenReturn("basicBackTemplate");

        //when
        String affectedFileName = fileSystemExportService.exportBasicReversedCardType(wordContextHolder);

        //then
        assertThat(affectedFileName, is(notNullValue()));
        assertThat(affectedFileName, is(equalTo("basic reversed saved file name")));

        verify(fileStorageService).appendExportToFile(expectedExportString, CardType.BASIC_REVERSED);
        verify(cardTemplateRenderService).renderBasicFrontTemplate(wordContextHolder);
        verify(cardTemplateRenderService).renderBasicBackTemplate(wordContextHolder);
        verifyNoMoreInteractions(fileStorageService, cardTemplateRenderService);
    }

    @Test
    public void testExportTypeInCardType() {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().withOriginalWord("word").build();
        String expectedExportString = "typeInFrontTemplate | typeInBackTemplate";

        when(fileStorageService.appendExportToFile(expectedExportString, CardType.TYPE_IN))
                .thenReturn("type in saved file name");
        when(cardTemplateRenderService.renderTypeInFrontTemplate(wordContextHolder)).thenReturn("typeInFrontTemplate");
        when(cardTemplateRenderService.renderTypeInBackTemplate(wordContextHolder)).thenReturn("typeInBackTemplate");

        //when
        String affectedFileName = fileSystemExportService.exportTypeInCardType(wordContextHolder);

        //then
        assertThat(affectedFileName, is(notNullValue()));
        assertThat(affectedFileName, is(equalTo("type in saved file name")));

        verify(fileStorageService).appendExportToFile(expectedExportString, CardType.TYPE_IN);
        verify(cardTemplateRenderService).renderTypeInFrontTemplate(wordContextHolder);
        verify(cardTemplateRenderService).renderTypeInBackTemplate(wordContextHolder);
        verifyNoMoreInteractions(fileStorageService, cardTemplateRenderService);
    }
}