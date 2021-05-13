package project.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMapDto {
    private SearchPoiInfo searchPoiInfo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SearchPoiInfo{
        private Pois pois;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pois{
        private List<Poi> poi;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Poi{
        private String name;
        private NewAddressList newAddressList;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NewAddressList{
        private List<NewAddress> newAddress;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NewAddress{
        private String centerLat;
        private String centerLon;
    }
}
