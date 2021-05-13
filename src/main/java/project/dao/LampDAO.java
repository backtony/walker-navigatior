package project.dao;

import org.apache.ibatis.annotations.Param;
import project.routes.model.Location;

import java.util.ArrayList;
import java.util.List;

public interface LampDAO {
//    public static List<Location> getLamps(double x1, double y1, double x2, double y2) {
//        List<Location> dummy = new ArrayList<>();
//        dummy.add(new Location(127.125, 37.51));
//        return dummy;
//    }
List<Location> getLampsInRange(
        @Param("x1") double x1,
        @Param("y1") double y1,
        @Param("x2") double x2,
        @Param("y2") double y2
);
}
