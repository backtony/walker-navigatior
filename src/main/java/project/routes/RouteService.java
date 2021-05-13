package project.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.dao.AccidentDAO;
import project.dao.LampDAO;
import project.routes.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    Environment env;
    @Autowired
    AccidentDAO accidentDAO;

    public PedestrianApiResponse getResponse(Double startX, Double startY, Double endX, Double endY, Double wayX,
                                             Double wayY) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("appKey", env.getProperty("tmap.appKey"));

        String url = "https://apis.openapi.sk.com/tmap/routes/pedestrian";
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("startX", startX.toString());
        map.add("startY", startY.toString());
        map.add("endY", endY.toString());
        map.add("endX", endX.toString());
        if (wayX != null) {
            map.add("passList", wayX+","+wayY);
        }
        map.add("startName", "start");
        map.add("endName", "end");
        HttpEntity<MultiValueMap> entity = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(url,entity , PedestrianApiResponse.class);
    }

    public List<Candidate> findCandidates(double startX, double startY, double endX, double endY) {
        PointBox pb = new PointBox(startX, startY, endX, endY);
        List<Candidate> bestCandidates = Arrays.asList(new Candidate[5]);
        List<Accident> accidents = accidentDAO.getAccidentsInRange(startX, startY, endX, endY);
        Accident[] accidentArr = new Accident[accidents.size()];
        for (int i=0; i<accidents.size(); i++) {
            accidentArr[i] = accidents.get(i);
        }
        System.out.println(accidents);
        Candidate noWayCand = new Candidate();
        noWayCand.setRoutes(getResponse(startX, startY, endX, endY, null, null));
        bestCandidates.set(0, noWayCand);
        for (Location lamp : LampDAO.getLamps(startX, startY, endX, endY)) {
            int res = pb.findSector(lamp.getX(), lamp.getY());
            if (res == 0) continue;
            Candidate cand = new Candidate();
            cand.setAccidents(accidentArr);
            cand.setRoutes(getResponse(startX, startY, endX, endY, lamp.getX(), lamp.getY()));
            cand.calculateCost();
            double cost = cand.getCost();
            System.out.println("x: "+lamp.getX());
            System.out.println(cost);
            if (bestCandidates.get(res) == null || cost < bestCandidates.get(res).getCost()) {
                bestCandidates.set(res, cand);
            }
        }
        return bestCandidates;
    }
}
