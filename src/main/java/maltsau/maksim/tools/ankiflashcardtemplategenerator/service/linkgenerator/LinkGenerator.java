package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator;

/**
 * Service for generating links.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public interface LinkGenerator {

    /**
     * Method for generating of links for given word
     * @param word for link generation
     * @return generated link
     */
    String generateLink(String word);
}
