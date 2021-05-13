package project.routes.model.properties;

public class PointProperties implements Properties {
    Integer index;
    Integer pointIndex;
    String name;
    String description;
    String direction;
    String intersectionName;
    String nearPoiX;
    String nearPoiY;
    String nearPoiName;
    Integer turnType;
    String pointType;
    Integer totalDistance;
    Integer totalTime;
    Integer facilityType;
    String facilityName;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(Integer pointIndex) {
        this.pointIndex = pointIndex;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIntersectionName() {
        return intersectionName;
    }

    public void setIntersectionName(String intersectionName) {
        this.intersectionName = intersectionName;
    }

    public String getNearPoiX() {
        return nearPoiX;
    }

    public void setNearPoiX(String nearPoiX) {
        this.nearPoiX = nearPoiX;
    }

    public String getNearPoiY() {
        return nearPoiY;
    }

    public void setNearPoiY(String nearPoiY) {
        this.nearPoiY = nearPoiY;
    }

    public String getNearPoiName() {
        return nearPoiName;
    }

    public void setNearPoiName(String nearPoiName) {
        this.nearPoiName = nearPoiName;
    }

    public Integer getTurnType() {
        return turnType;
    }

    public void setTurnType(Integer turnType) {
        this.turnType = turnType;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
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
        return "PointProperties{" +
                "index=" + index +
                ", pointIndex=" + pointIndex +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", direction='" + direction + '\'' +
                ", intersectionName='" + intersectionName + '\'' +
                ", nearPoiX='" + nearPoiX + '\'' +
                ", nearPoiY='" + nearPoiY + '\'' +
                ", nearPoiName='" + nearPoiName + '\'' +
                ", turnType=" + turnType +
                ", pointType='" + pointType + '\'' +
                ", totalDistance=" + totalDistance +
                ", totalTime=" + totalTime +
                ", facilityType=" + facilityType +
                ", facilityName='" + facilityName + '\'' +
                '}';
    }
}
