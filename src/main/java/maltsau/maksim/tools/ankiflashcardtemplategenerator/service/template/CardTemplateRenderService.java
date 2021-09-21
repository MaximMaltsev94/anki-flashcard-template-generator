package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.template;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;

/**
 * Service for rendering card templates.
 * Date: 20.09.2021
 *
 * @author Maksim Maltsau
 */
public interface CardTemplateRenderService {

    /**
     * Renders front of `Basic` card type.
     * @param wordContextHolder word information
     * @return rendered card as {@link String}
     */
    String renderBasicFrontTemplate(WordContextHolder wordContextHolder);

    /**
     * Renders back of `Basic` card type.
     * @param wordContextHolder word information
     * @return rendered card as {@link String}
     */
    String renderBasicBackTemplate(WordContextHolder wordContextHolder);

    /**
     * Renders front of `Type in` card type.
     * @param wordContextHolder word information
     * @return rendered card as {@link String}
     */
    String renderTypeInFrontTemplate(WordContextHolder wordContextHolder);

    /**
     * Renders back of `Type in` card type.
     * @param wordContextHolder word information
     * @return rendered card as {@link String}
     */
    String renderTypeInBackTemplate(WordContextHolder wordContextHolder);
}
