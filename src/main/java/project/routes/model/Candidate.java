package project.routes.model;

import java.util.Arrays;

public class Candidate {
    PedestrianApiResponse routes;
    Float cost;
    Accident[] accidents;
    Location[] cctvs;
    Location[] lamps;

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
