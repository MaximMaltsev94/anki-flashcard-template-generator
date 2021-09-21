package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.export;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;

import java.util.Set;

/**
 * Service for exporting cards.
 * Date: 20.09.2021
 *
 * @author Maksim Maltsau
 */
public interface ExportService {

    /**
     * Exports all card types for given word.
     * Generates `front` and `back` cards.
     *
     * @param wordContextHolder word information holder object.
     * @return Set of updated file names.
     */
    Set<String> exportAllCardTypes(WordContextHolder wordContextHolder);

    /**
     * Exports word as `Basic` card type.
     * Generates `front` and `back` cards.
     *
     * @param wordContextHolder word information holder object.
     * @return Updated file name.
     */
    String exportBasicCardType(WordContextHolder wordContextHolder);

    /**
     * Exports word as `Basic Reversed` card type.
     * Generates `front` and `back` cards.
     *
     * @param wordContextHolder word information holder object.
     * @return Updated file name.
     */
    String exportBasicReversedCardType(WordContextHolder wordContextHolder);

    /**
     * Exports word as `Type in` card type.
     * Generates `front` and `back` cards.
     *
     * @param wordContextHolder word information holder object.
     * @return Updated file name.
     */
    String exportTypeInCardType(WordContextHolder wordContextHolder);
}
