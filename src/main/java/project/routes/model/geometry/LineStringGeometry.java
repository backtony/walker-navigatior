package project.routes.model.geometry;

import java.util.Arrays;

public class LineStringGeometry implements Geometry {
    String type;
    String[][] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "LineStringGeometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
