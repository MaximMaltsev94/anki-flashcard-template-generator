package maltsau.maksim.tools.ankiflashcardtemplategenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class which handle index page request.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndexPage() {
        return "index";
    }
}
