package maltsau.maksim.tools.ankiflashcardtemplategenerator.controller;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.WordContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/generate-anki-flashcards")
public class AnkiCardsGeneratorController {

    private final WordContextService wordContextService;

    @Autowired
    public AnkiCardsGeneratorController(WordContextService wordContextService) {
        this.wordContextService = wordContextService;
    }

    @PostMapping
    public String generateAnkiFlashcards(@RequestParam("originalWord") String originalWord,
                                         @RequestParam("originalContext") String originalContext,
                                         @RequestParam("translation") String translation,
                                         @RequestParam("translatedContext") String translatedContext,
                                         @RequestParam("pronunciationUri") String pronunciationUri,
                                         Model model) {
        WordContextHolder wordContextHolder = wordContextService.generateContext(originalWord, originalContext,
                translation, translatedContext, pronunciationUri);
        model.addAttribute("wordContextHolder", wordContextHolder);
        return "ankiCardsResults";
    }
}
