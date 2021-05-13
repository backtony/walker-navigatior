package com.example.jsontototototototo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// x,y만
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class XyLocation {
    private String name;
    private String x;
    private String y;
}
