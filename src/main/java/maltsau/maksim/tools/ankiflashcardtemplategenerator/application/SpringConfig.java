package maltsau.maksim.tools.ankiflashcardtemplategenerator.application;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.LinkGenerator;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator.SimpleTemplateLinkGeneratorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring configuration class.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@ComponentScan(basePackages = "maltsau.maksim.tools.ankiflashcardtemplategenerator")
public class SpringConfig {

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
}
