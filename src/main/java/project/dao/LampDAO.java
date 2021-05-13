package project.dao;

import project.routes.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LampDAO {
    public static List<Location> getLamps(double x1, double y1, double x2, double y2) {
        List<Location> dummy = new ArrayList<>();
        dummy.add(new Location(127.125, 37.51));
        return dummy;
    }
}
