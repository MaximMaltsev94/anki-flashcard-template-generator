package maltsau.maksim.tools.ankiflashcardtemplategenerator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot entry point.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@SpringBootApplication
@Import(SpringConfig.class)
public class AnkiFlashcardTemplateGeneratorApplication {

    /**
     * Main method of Spring boot application
     * @param args command line args
     */
    public static void main(String[] args) {
        SpringApplication.run(AnkiFlashcardTemplateGeneratorApplication.class, args);
    }

}
