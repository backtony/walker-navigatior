package project.routes.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import project.routes.model.feature.Feature;

import java.util.Arrays;

@JsonDeserialize(using= PedestrianApiResponseDeserializer.class)
public class PedestrianApiResponse {
    String type;
    Feature[] features;

    public PedestrianApiResponse(String type, Feature[] features) {
        this.type = type;
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
