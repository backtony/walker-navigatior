package project.routes.model.properties;

public class LineStringProperties implements Properties {
    Integer index;
    Integer lineIndex;
    String name;
    String description;
    String time;
    String distance;
    Integer roadType;
    String categoryRoadType;
    Integer facilityType;
    String facilityName;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(Integer lineIndex) {
        this.lineIndex = lineIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getRoadType() {
        return roadType;
    }

    public void setRoadType(Integer roadType) {
        this.roadType = roadType;
    }

    public String getCategoryRoadType() {
        return categoryRoadType;
    }

    public void setCategoryRoadType(String categoryRoadType) {
        this.categoryRoadType = categoryRoadType;
    }

    public Integer getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(Integer facilityType) {
        this.facilityType = facilityType;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return "LineStringProperties{" +
                "index=" + index +
                ", lineIndex=" + lineIndex +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", distance='" + distance + '\'' +
                ", roadType=" + roadType +
                ", categoryRoadType='" + categoryRoadType + '\'' +
                ", facilityType=" + facilityType +
                ", facilityName='" + facilityName + '\'' +
                '}';
    }
}
