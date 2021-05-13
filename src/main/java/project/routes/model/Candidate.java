package project.routes.model;

import java.util.Arrays;

import project.routes.RouteService;
import project.routes.model.feature.LineStringFeature;
import project.routes.model.feature.PointFeature;

public class Candidate {
    PedestrianApiResponse routes;
    double cost;
    Accident[] accidents;
    Location[] cctvs;
    Location[] lamps;

    public double euclidDistance(String t1,String t2, double x2, double y2){
        double x1 = Double.valueOf(t1);
        double y1 = Double.valueOf(t2);
        return ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2));
    }

    public double calculateCost(){
        // accidents와 ROUTES를 돌면서 최단 점을 찾고 그것을 COST에 합한다.
        // ROUTES는 POINT OR LINE이냐를 판단하고 포인트면 하나 아니면, 둘이다.
        // CANDIDATE 코스트만을 저장하면 될듯.
        this.cost = 0;
        for(int i = 0; i < accidents.length; i++){
            Location loc = new Location();
            loc.setX(181); loc.setY(181);
            double distance = 10000;
            // Double.valueOf(s_num)
            for(int j = 0; j < routes.features.length; j++){
                if (routes.features[i] instanceof PointFeature) {
                    PointFeature feature = (PointFeature) routes.features[i];
                    double x = Double.valueOf(feature.getGeometry().getCoordinates()[0]);
                    double y = Double.valueOf(feature.getGeometry().getCoordinates()[1]);
                    double tmp = euclidDistance(accidents[i].getX(),accidents[i].getY(),x,y);
                    if(tmp < distance){
                        loc.setX(x); loc.setY(y);
                        distance = tmp;
                    }
                } else {
                    LineStringFeature feature = (LineStringFeature) routes.features[i];
                    for(int k = 0; k < 2; k++){
                        double x = Double.valueOf(feature.getGeometry().getCoordinates()[i][0]);
                        double y = Double.valueOf(feature.getGeometry().getCoordinates()[i][1]);
                        double tmp = euclidDistance(accidents[i].getX(),accidents[i].getY(),x,y);
                        if(tmp < distance){
                            loc.setX(x); loc.setY(y);
                            distance = tmp;
                        }
                    }
                }
            }
            double accidentCnt = (double)accidents[i].getAccidentCnt();
            double deathCnt = (double)accidents[i].getDeathCnt();
            this.cost += accidentCnt*(deadCnt+1)/distance;
        }

        return this.cost;
    }

    public PedestrianApiResponse getRoutes() {
        return routes;
    }

    public void setRoutes(PedestrianApiResponse routes) {
        this.routes = routes;
    }

    public Float getCost() {
        return 1.0f;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Accident[] getAccidents() {
        return accidents;
    }

    public void setAccidents(Accident[] accidents) {
        this.accidents = accidents;
    }

    public Location[] getCctvs() {
        return cctvs;
    }

    public void setCctvs(Location[] cctvs) {
        this.cctvs = cctvs;
    }

    public Location[] getLamps() {
        return lamps;
    }

    public void setLamps(Location[] lamps) {
        this.lamps = lamps;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "routes=" + routes +
                ", cost=" + cost +
                ", accidents=" + Arrays.toString(accidents) +
                ", cctvs=" + Arrays.toString(cctvs) +
                ", lamps=" + Arrays.toString(lamps) +
                '}';
    }
}
