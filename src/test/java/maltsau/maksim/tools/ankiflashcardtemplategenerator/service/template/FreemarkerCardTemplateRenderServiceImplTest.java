package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.template;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.BaseTestNGMockSetup;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class FreemarkerCardTemplateRenderServiceImplTest extends BaseTestNGMockSetup {

    private static final String WORD_HOLDER_MODEL_NAME = "wordContextHolder";

    @Mock
    private Template basicFrontTemplate;

    @Mock
    private Template basicBackTemplate;

    @Mock
    private Template typeInFrontTemplate;

    @Mock
    private Template typeInBackTemplate;

    private FreemarkerCardTemplateRenderServiceImpl templateRenderService;

    @BeforeMethod
    public void setUp() {
        Mockito.reset(basicFrontTemplate, basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
        templateRenderService = new FreemarkerCardTemplateRenderServiceImpl(basicFrontTemplate,
                basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
    }

    @Test
    public void testRenderBasicFrontTemplate() throws TemplateException, IOException {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().build();
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);

        //when
        templateRenderService.renderBasicFrontTemplate(wordContextHolder);

        //then
        verify(basicFrontTemplate).process(eq(model), any());
        verifyNoMoreInteractions(basicFrontTemplate, basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
    }

    @Test
    public void testRenderBasicBackTemplate() throws TemplateException, IOException {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().build();
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);

        //when
        templateRenderService.renderBasicBackTemplate(wordContextHolder);

        //then
        verify(basicBackTemplate).process(eq(model), any());
        verifyNoMoreInteractions(basicFrontTemplate, basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
    }

    @Test
    public void testRenderTypeInFrontTemplate() throws TemplateException, IOException {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().build();
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);

        //when
        templateRenderService.renderTypeInFrontTemplate(wordContextHolder);

        //then
        verify(typeInFrontTemplate).process(eq(model), any());
        verifyNoMoreInteractions(basicFrontTemplate, basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
    }

    @Test
    public void testRenderTypeInBackTemplate() throws TemplateException, IOException {
        //given
        WordContextHolder wordContextHolder = WordContextHolder.builder().build();
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);

        //when
        templateRenderService.renderTypeInBackTemplate(wordContextHolder);

        //then
        verify(typeInBackTemplate).process(eq(model), any());
        verifyNoMoreInteractions(basicFrontTemplate, basicBackTemplate, typeInFrontTemplate, typeInBackTemplate);
    }
}