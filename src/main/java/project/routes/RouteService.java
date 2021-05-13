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
import project.dao.CctvDAO;
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
    @Autowired
    CctvDAO cctvDAO;

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
        double llX = Math.min(startX, endX), llY = Math.min(startY, endY),
                urX=Math.max(startX, endX), urY=Math.max(startY, endY);
        PointBox pb = new PointBox(startX, startY, endX, endY);
        List<Candidate> bestCandidates = Arrays.asList(new Candidate[5]);
        List<Accident> accidents = accidentDAO.getAccidentsInRange(llX, llY, urX, urY);
        Accident[] accidentArr = new Accident[accidents.size()];
        for (int i=0; i<accidents.size(); i++) {
            accidentArr[i] = accidents.get(i);
        }
        List<Location> cctvs = cctvDAO.getCctvsInRange(llX, llY, urX, urY);
        Location[] cctvArr = new Location[cctvs.size()];
        for (int i=0; i<cctvs.size(); i++) {
            cctvArr[i] = cctvs.get(i);
        }
        Candidate noWayCand = new Candidate();
        noWayCand.setAccidents(accidentArr);
        noWayCand.setRoutes(getResponse(startX, startY, endX, endY, null, null));
        noWayCand.calculateCost();
        bestCandidates.set(0, noWayCand);
        for (Location lamp : LampDAO.getLamps(llX, llY, urX, urY)) {
            int res = pb.findSector(lamp.getX(), lamp.getY());
            if (res == 0) continue;
            Candidate cand = new Candidate();
            cand.setAccidents(accidentArr);
            cand.setRoutes(getResponse(startX, startY, endX, endY, lamp.getX(), lamp.getY()));
            cand.calculateCost();
            double cost = cand.getCost();
            if (bestCandidates.get(res) == null || cost < bestCandidates.get(res).getCost()) {
                bestCandidates.set(res, cand);
            }
        }
        for (Candidate cand : bestCandidates) {
            if (cand != null) {
                cand.setCctvs(cctvArr);
            }
        }
        return bestCandidates;
    }
}
