package project.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project.controller.AccidentController;
import project.util.PropertyUtil;

@Controller
public class MapController {
    @Autowired AccidentController accidentController;
    @GetMapping("/")
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("appKey", PropertyUtil.getProperty("tmap.appKey"));
        System.out.println();
        return mav;
    }
}
