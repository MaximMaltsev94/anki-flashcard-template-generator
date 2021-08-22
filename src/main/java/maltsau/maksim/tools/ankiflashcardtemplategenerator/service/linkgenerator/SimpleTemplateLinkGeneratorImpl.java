package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SimpleTemplateLinkGeneratorImpl implements LinkGenerator {

    private final String template;

    public SimpleTemplateLinkGeneratorImpl(String template) {
        this.template = template;
    }

    @Override
    public String generateLink(String word) {
        String encodedWord = URLEncoder.encode(word, StandardCharsets.UTF_8);
        return String.format(template, encodedWord);
    }
}
