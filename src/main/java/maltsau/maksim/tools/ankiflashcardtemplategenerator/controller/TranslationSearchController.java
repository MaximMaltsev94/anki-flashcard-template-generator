package maltsau.maksim.tools.ankiflashcardtemplategenerator.controller;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.TranslationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class which handles requests searching of translation information.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@Controller
@RequestMapping("/search-translation")
public class TranslationSearchController {

    private final TranslationSearchService translationSearchService;

    @Autowired
    public TranslationSearchController(TranslationSearchService translationSearchService) {
        this.translationSearchService = translationSearchService;
    }

    /**
     * Method for handling GET request for word translation search.
     * @param word query param
     * @param model for result view
     * @return name of the view for displaying search results
     */
    @GetMapping
    public String search(@RequestParam(name = "word") String word, Model model) {
        TranslationSearchResult translationSearchResult = translationSearchService.searchTranslation(word);
        model.addAttribute("translationResult", translationSearchResult);
        return "searchResults";
    }
}
