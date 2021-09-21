package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.template;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.exception.CardTemplateRenderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


/**
 * Implementation of {@link CardTemplateRenderService} which renders templates using FreeMarker.
 * Date: 20.09.2021
 *
 * @author Maksim Maltsau
 */
@Service
public class FreemarkerCardTemplateRenderServiceImpl implements CardTemplateRenderService {

    private static final String WORD_HOLDER_MODEL_NAME = "wordContextHolder";
    private static final String TEMPLATE_RENDER_ERROR_MSG = "Error while rendering template: %s";

    private final Template basicFrontTemplate;
    private final Template basicBackTemplate;
    private final Template typeInFrontTemplate;
    private final Template typeInBackTemplate;

    @Autowired
    public FreemarkerCardTemplateRenderServiceImpl(@Qualifier("basicFrontCardTemplate")
                                                           Template basicFrontTemplate,
                                                   @Qualifier("basicBackCardTemplate")
                                                           Template basicBackTemplate,
                                                   @Qualifier("typeInFrontCardTemplate")
                                                           Template typeInFrontTemplate,
                                                   @Qualifier("typeInBackCardTemplate")
                                                           Template typeInBackTemplate) {
        this.basicFrontTemplate = basicFrontTemplate;
        this.basicBackTemplate = basicBackTemplate;
        this.typeInFrontTemplate = typeInFrontTemplate;
        this.typeInBackTemplate = typeInBackTemplate;
    }

    @Override
    public String renderBasicFrontTemplate(WordContextHolder wordContextHolder) {
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);
        return renderTemplateToString(basicFrontTemplate, model);
    }

    @Override
    public String renderBasicBackTemplate(WordContextHolder wordContextHolder) {
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);
        return renderTemplateToString(basicBackTemplate, model);
    }

    @Override
    public String renderTypeInFrontTemplate(WordContextHolder wordContextHolder) {
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);
        return renderTemplateToString(typeInFrontTemplate, model);
    }

    @Override
    public String renderTypeInBackTemplate(WordContextHolder wordContextHolder) {
        Map<String, Object> model = Map.of(WORD_HOLDER_MODEL_NAME, wordContextHolder);
        return renderTemplateToString(typeInBackTemplate, model);
    }

    private String renderTemplateToString(Template template, Map<String, Object> model) {
        try (Writer out = new StringWriter()) {
            template.process(model, out);
            return out.toString();
        } catch (IOException | TemplateException e) {
            throw new CardTemplateRenderServiceException(
                    String.format(TEMPLATE_RENDER_ERROR_MSG, template.getName()), e);
        }
    }
}
