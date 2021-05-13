package project.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project.util.PropertyUtil;

@Controller
public class MapController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("appKey", PropertyUtil.getProperty("tmap.appKey"));
        return mav;
    }
}
