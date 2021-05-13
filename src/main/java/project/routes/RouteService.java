package project.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.routes.model.Candidate;
import project.routes.model.PedestrianApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    Environment env;

    public PedestrianApiResponse getResponse(String startX, String startY, String endX, String endY) {
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
        return restTemplate.postForObject(url,entity , PedestrianApiResponse.class);
    }

    public List<Candidate> findCandidates(String startX, String startY, String endX, String endY) {
        List<Candidate> candidates = new ArrayList<>();
        //todo : generate candidates
        candidates.add(new Candidate());
        Candidate cand = new Candidate();
        cand.setRoutes(getResponse(startX, startY, endX, endY));
        candidates.add(cand);
        return candidates;
    }
}
