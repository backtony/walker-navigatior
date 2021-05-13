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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import project.routes.model.PedestrianApiResponse;

@Controller
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RoutesController {

    @Autowired
    Environment env;

    @ModelAttribute("appKey")
    String appKey() {
        return env.getProperty("app.key");
    }

    @GetMapping("/")
    public String path(Model model,
                       @RequestParam String startX,
                       @RequestParam String startY,
                       @RequestParam String endX,
                       @RequestParam String endY) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("appKey", env.getProperty("app.key"));

            String url = "https://apis.openapi.sk.com/tmap/routes/pedestrian";
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.add("startY", startY);
            map.add("startX", startX);
            map.add("endY", endY);
            map.add("endX", endX);
            map.add("startName", "start");
            map.add("endName", "end");
            HttpEntity<MultiValueMap> entity = new HttpEntity<>(map, headers);
            PedestrianApiResponse res = restTemplate.postForObject(url,entity , PedestrianApiResponse.class);
            System.out.println(res);
            model.addAttribute("res", res);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "index";
    }
}
