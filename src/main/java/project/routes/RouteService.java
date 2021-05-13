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
import project.routes.model.Candidate;
import project.routes.model.PedestrianApiResponse;
import project.util.PropertyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    LampDAO lampDAO;

    public PedestrianApiResponse getResponse(Double startX, Double startY, Double endX, Double endY, Double wayX,
                                             Double wayY) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("appKey", PropertyUtil.getProperty("tmap.appKey"));

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
        double llX = Math.min(startX, endX), llY = Math.min(startY, endY), // lower left
                urX=Math.max(startX, endX), urY=Math.max(startY, endY); // upper right
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
        List<Location> lamps = lampDAO.getLampsInRange(llX, llY, urX, urY);
        Collections.shuffle(lamps);
        Location[] lampArr = new Location[lamps.size()];
        for (int i=0; i<lamps.size(); i++) {
            lampArr[i] = lamps.get(i);
        }

        // candidate with no waypoint
        Candidate noWayCand = new Candidate();
        noWayCand.setAccidents(accidentArr);
        noWayCand.setRoutes(getResponse(startX, startY, endX, endY, null, null));
        noWayCand.setLamps(lampArr);
        noWayCand.calculateCost();
        bestCandidates.set(0, noWayCand);

        int[] cnt = {0,0,0,0,0};
        int reqCnt = 1;
        long startTime = System.nanoTime();
        for (Location lamp : lamps) {
            int res = pb.findSector(lamp.getX(), lamp.getY());
            if (res == 0) continue;
            // only check up to 3 candidates per sector
            if (cnt[res] > 3) continue;
            cnt[res]++;
            reqCnt++;
            if (reqCnt == 2) {
                long elapsed = System.nanoTime() - startTime;
                if (elapsed < 1000000) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5000 - elapsed/1000000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                startTime = System.nanoTime();
                reqCnt = 0;
            }
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
                cand.setLamps(lampArr);
            }
        }
        return bestCandidates;
    }
}
