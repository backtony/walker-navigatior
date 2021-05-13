package project.routes.model.feature;

import project.routes.model.geometry.LineStringGeometry;
import project.routes.model.properties.LineStringProperties;

public class LineStringFeature implements Feature {
    String type;
    LineStringGeometry geometry;
    LineStringProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LineStringGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(LineStringGeometry geometry) {
        this.geometry = geometry;
    }

    public LineStringProperties getProperties() {
        return properties;
    }

    public void setProperties(LineStringProperties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "LineStringFeature{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
