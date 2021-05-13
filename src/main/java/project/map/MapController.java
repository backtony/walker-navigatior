package project.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import project.util.PropertyUtil;

@Slf4j
@Controller
public class MapController {
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
