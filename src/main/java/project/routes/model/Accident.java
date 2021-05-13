package project.routes.model;

public class Accident {
    private int id;
    private String placeName;
    private String addressName;
    private String roadAddressName;
    private double x;
    private double y;
    private int accidentCnt;
    private int deadCnt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getRoadAddressName() {
        return roadAddressName;
    }

    public void setRoadAddressName(String roadAddressName) {
        this.roadAddressName = roadAddressName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getAccidentCnt() {
        return accidentCnt;
    }

    public void setAccidentCnt(int accidentCnt) {
        this.accidentCnt = accidentCnt;
    }

    public int getDeadCnt() {
        return deadCnt;
    }

    public void setDeadCnt(int deadCnt) {
        this.deadCnt = deadCnt;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "id=" + id +
                ", placeName='" + placeName + '\'' +
                ", addressName='" + addressName + '\'' +
                ", roadAddressName='" + roadAddressName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", accidentCnt=" + accidentCnt +
                ", deadCnt=" + deadCnt +
                '}';
    }
}
