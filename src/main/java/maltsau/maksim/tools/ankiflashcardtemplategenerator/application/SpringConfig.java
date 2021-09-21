package maltsau.maksim.tools.ankiflashcardtemplategenerator.application;

import freemarker.cache.ClassTemplateLoader;
import freemarker.core.PlainTextOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.LinkGenerator;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.SimpleTemplateLinkGeneratorImpl;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * Spring configuration class.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@ComponentScan(basePackages = "maltsau.maksim.tools.ankiflashcardtemplategenerator")
public class SpringConfig {

    private static final String TEMPLATE_CREATION_ERROR_MSG = "Error while creating template: `%s`";

    private static final String TEMPLATE_PATH = "templates/export";
    private static final String BASIC_FRONT_TEMPLATE_PATH = TEMPLATE_PATH + "/basicFront.ftlh";
    private static final String BASIC_BACK_TEMPLATE_PATH = TEMPLATE_PATH + "/basicBack.ftlh";
    private static final String TYPE_IN_FRONT_TEMPLATE_PATH = TEMPLATE_PATH + "/typeInFront.ftlh";
    private static final String TYPE_IN_BACK_TEMPLATE_PATH = TEMPLATE_PATH + "/typeInBack.ftlh";

    @Bean(name = "reversoContextLinkGenerator")
    @Value("${linkgenerator.reversocontext.uri}")
    public LinkGenerator reversoContextLinkGenerator(String uriTemplate) {
        return new SimpleTemplateLinkGeneratorImpl(uriTemplate);
    }

    @Bean(name = "googleTranslateLinkGenerator")
    @Value("${linkgenerator.googletranslate.uri}")
    public LinkGenerator googleTranslateLinkGenerator(String uriTemplate) {
        return new SimpleTemplateLinkGeneratorImpl(uriTemplate);
    }

    @Bean(name = "pronunciationLinkGenerator")
    @Value("${linkgenerator.pronunciation.uri}")
    public LinkGenerator pronunciationLinkGenerator(String uriTemplate) {
        return new SimpleTemplateLinkGeneratorImpl(uriTemplate);
    }

    @Bean(name = "exportFreeMarkerConfiguration")
    public Configuration exportFreeMarkerConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/"));
        cfg.setAutoEscapingPolicy(Configuration.DISABLE_AUTO_ESCAPING_POLICY);
        cfg.setOutputFormat(PlainTextOutputFormat.INSTANCE);
        return cfg;
    }

    @Bean(name = "basicFrontCardTemplate")
    public Template basicFrontCardTemplate(@Qualifier("exportFreeMarkerConfiguration") Configuration configuration) {
        return createTemplate(configuration, BASIC_FRONT_TEMPLATE_PATH);
    }

    @Bean(name = "basicBackCardTemplate")
    public Template basicBackCardTemplate(@Qualifier("exportFreeMarkerConfiguration") Configuration configuration) {
        return createTemplate(configuration, BASIC_BACK_TEMPLATE_PATH);
    }

    @Bean(name = "typeInFrontCardTemplate")
    public Template typeInFrontCardTemplate(@Qualifier("exportFreeMarkerConfiguration")
                                                    Configuration configuration) {
        return createTemplate(configuration, TYPE_IN_FRONT_TEMPLATE_PATH);
    }

    @Bean(name = "typeInBackCardTemplate")
    public Template typeInBackCardTemplate(@Qualifier("exportFreeMarkerConfiguration") Configuration configuration) {
        return createTemplate(configuration, TYPE_IN_BACK_TEMPLATE_PATH);
    }

    private Template createTemplate(Configuration configuration, String templateName) {
        try {
            return configuration.getTemplate(templateName);
        } catch (IOException e) {
            throw new BeanInitializationException(String.format(TEMPLATE_CREATION_ERROR_MSG, templateName), e);
        }
    }
}
