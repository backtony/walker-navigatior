package project.routes.model;

import java.util.Arrays;
import java.util.List;

import project.routes.RouteService;
import project.routes.model.feature.LineStringFeature;
import project.routes.model.feature.PointFeature;

public class Candidate {
    PedestrianApiResponse routes;
    double cost;
    Accident[] accidents;
    Location[] cctvs;
    Location[] lamps;

    public double euclidDistance(double x1,double y1, double x2, double y2){
//        double x1 = Double.valueOf(t1);
//        double y1 = Double.valueOf(t2);
        return ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2));
    }

    public double calculateCost(){
        // accidents와 ROUTES를 돌면서 최단 점을 찾고 그것을 COST에 합한다.
        // ROUTES는 POINT OR LINE이냐를 판단하고 포인트면 하나 아니면, 둘이다.
        // CANDIDATE 코스트만을 저장하면 될듯.
        this.cost = 0;
        System.out.println("len:"+accidents.length);
        for(int i = 0; i < accidents.length; i++){
            double distance = 10000;
            // Double.valueOf(s_num)
            for(int j = 0; j < routes.features.length; j++){
                if (routes.features[i] instanceof PointFeature) {
                    PointFeature feature = (PointFeature) routes.features[i];
                    double x = Double.valueOf(feature.getGeometry().getCoordinates()[0]);
                    double y = Double.valueOf(feature.getGeometry().getCoordinates()[1]);
                    double tmp = euclidDistance(accidents[i].getX(),accidents[i].getY(),x,y);
                    System.out.println("distance:"+tmp);
                    if(tmp < distance){
//                        loc.setX(x); loc.setY(y);
                        distance = tmp;
                    }
                } else {
                    LineStringFeature feature = (LineStringFeature) routes.features[i];
                    for(int k = 0; k < 2; k++){
                        double x = Double.valueOf(feature.getGeometry().getCoordinates()[k][0]);
                        double y = Double.valueOf(feature.getGeometry().getCoordinates()[k][1]);
                        double tmp = euclidDistance(accidents[i].getX(),accidents[i].getY(),x,y);
                        System.out.println("distance:"+tmp);
                        if(tmp < distance){
//                            loc.setX(x); loc.setY(y);
                            distance = tmp;
                        }
                    }
                }
            }
            double accidentCnt = (double)accidents[i].getAccidentCnt();
            double deadCnt = (double)accidents[i].getDeadCnt();
            this.cost += accidentCnt*(deadCnt+1)/distance;
        }
        System.out.println(this.cost);
        return this.cost;
    }

    public PedestrianApiResponse getRoutes() {
        return routes;
    }

    public void setRoutes(PedestrianApiResponse routes) {
        this.routes = routes;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
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
