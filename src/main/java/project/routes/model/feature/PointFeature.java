package project.routes.model.feature;

import project.routes.model.geometry.PointGeometry;
import project.routes.model.properties.PointProperties;

public class PointFeature implements Feature {
    String type;
    PointGeometry geometry;
    PointProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PointGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PointGeometry geometry) {
        this.geometry = geometry;
    }

    public PointProperties getProperties() {
        return properties;
    }

    public void setProperties(PointProperties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "PointFeature{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
