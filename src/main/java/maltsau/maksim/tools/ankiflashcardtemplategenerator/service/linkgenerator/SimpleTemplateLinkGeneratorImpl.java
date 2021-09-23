package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator;

import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of {@link LinkGenerator} which simply replace placeholder in given template.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public class SimpleTemplateLinkGeneratorImpl implements LinkGenerator {

    private final String template;

    public SimpleTemplateLinkGeneratorImpl(String template) {
        this.template = template;
    }

    @Override
    public String generateLink(String word) {
        if (!StringUtils.hasText(word)) {
            return String.format(template, "");
        }
        String encodedWord = URLEncoder.encode(word, StandardCharsets.UTF_8);
        return String.format(template, encodedWord);
    }
}
