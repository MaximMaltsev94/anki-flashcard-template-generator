package maltsau.maksim.tools.ankiflashcardtemplategenerator.controller;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.TranslationSearchResult;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.TranslationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search-translation")
public class TranslationSearchController {

    private final TranslationSearchService translationSearchService;

    @Autowired
    public TranslationSearchController(TranslationSearchService translationSearchService) {
        this.translationSearchService = translationSearchService;
    }

    @GetMapping
    public String search(@RequestParam(name = "word") String word, Model model) {
        TranslationSearchResult translationSearchResult = translationSearchService.searchTranslation(word);
        model.addAttribute("translationResult", translationSearchResult);
        return "searchResults";
    }
}
