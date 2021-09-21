package maltsau.maksim.tools.ankiflashcardtemplategenerator.controller;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.export.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

/**
 * Controller for handling card export commands.
 * Date: 19.09.2021
 *
 * @author Maksim Maltsau
 */
@Controller
@RequestMapping("/export-anki-flashcards")
public class ExportController {

    private final ExportService exportService;

    @Autowired
    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    /**
     * Method for handling POST request for exporting of flash cards.
     *
     * @return export results as JSON object
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> exportAnkiFlashcards(@RequestBody WordContextHolder wordContextHolder) {
        Set<String> affectedFileNames = exportService.exportAllCardTypes(wordContextHolder);

        return Map.of(
                "message", "Ok",
                "affectedFileNames", affectedFileNames
        );
    }

}
