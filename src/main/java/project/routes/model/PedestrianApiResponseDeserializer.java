package project.routes.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import project.routes.model.PedestrianApiResponse;
import project.routes.model.feature.Feature;
import project.routes.model.feature.LineStringFeature;
import project.routes.model.feature.PointFeature;

import java.io.IOException;
import java.util.ArrayList;

public class PedestrianApiResponseDeserializer extends StdDeserializer<PedestrianApiResponse> {

    public PedestrianApiResponseDeserializer() {
        this(null);
    }

    public PedestrianApiResponseDeserializer(Class<?> feature) {
        super(feature);
    }

    @Override
    public PedestrianApiResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        String featureType = jsonNode.get("type").textValue();

        ArrayList<Feature> features = new ArrayList<>();
        JsonNode featuresNodes = jsonNode.get("features");

        for (final JsonNode feature : featuresNodes) {
            String type = feature.get("geometry").get("type").textValue();
            if ("Point".equals(type)) {
                features.add(mapper.convertValue(feature, PointFeature.class));
            } else if ("LineString".equals(type)) {
                features.add(mapper.convertValue(feature, LineStringFeature.class));
            }
        }
        return new PedestrianApiResponse(featureType, features.toArray(new Feature[features.size()]));
    }

}
