package project.routes.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import project.routes.model.feature.Feature;

import java.util.Arrays;

@JsonDeserialize(using= PedestrianApiResponseDeserializer.class)
@JsonSerialize(using = PedestrianApiResponseSerializer.class)
public class PedestrianApiResponse {
    String type;
    Feature[] features;

    public PedestrianApiResponse(String type, Feature[] features) {
        this.type = type;
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "PedestrianApiResponse{" +
                "type='" + type + '\'' +
                ", features=" + Arrays.toString(features) +
                '}';
    }
}
