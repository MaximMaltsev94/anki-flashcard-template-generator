package maltsau.maksim.tools.ankiflashcardtemplategenerator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringConfig.class)
public class AnkiFlashcardTemplateGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnkiFlashcardTemplateGeneratorApplication.class, args);
    }

}
