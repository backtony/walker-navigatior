package project.routes.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import project.routes.model.feature.Feature;

import java.io.IOException;

public class PedestrianApiResponseSerializer extends StdSerializer<PedestrianApiResponse> {
    public PedestrianApiResponseSerializer() {
        this(null);
    }

    public PedestrianApiResponseSerializer(Class<PedestrianApiResponse> t) {
        super(t);
    }

    @Override
    public void serialize(
            PedestrianApiResponse obj, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("type", obj.getType());
        jgen.writeFieldName("features");
        jgen.writeStartArray();
        for (Feature feature : obj.getFeatures()){
            jgen.writeObject(feature);
        }
        jgen.writeEndArray();
        jgen.writeStringField("itemName", "1");
        jgen.writeNumberField("owner", 2);
        jgen.writeEndObject();
    }
}

