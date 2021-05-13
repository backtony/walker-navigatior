package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter // Getter 생성
@Setter // Setter 생성
public class AccidentDTO {
    public AccidentDTO() {

    }
    private int id;
    private String placeName;
    private String addressName;
    private String roadAddressName;
    private double x;
    private double y;
    private int accidentCnt;
    private int deadCnt;



}
