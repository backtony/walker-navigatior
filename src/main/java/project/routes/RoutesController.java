package project.routes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import project.routes.model.Candidate;
import project.routes.model.PedestrianApiResponse;

import java.util.List;

@Controller
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RoutesController {

    @Autowired
    Environment env;

    @Autowired
    RouteService routeService;

    @ModelAttribute("appKey")
    String appKey() {
        return env.getProperty("app.key");
    }

    @ResponseBody
    @GetMapping("/path")
    public List<Candidate> path(
                          @RequestParam double startX,
                          @RequestParam double startY,
                          @RequestParam double endX,
                          @RequestParam double endY) {
        return routeService.findCandidates(startX, startY, endX, endY);
    }
}
