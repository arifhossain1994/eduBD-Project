package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends BaseModel{
    private String schoolName;
    private String schoolEmail;
    private String schoolStreet;
    private String schoolHouse;
    private String schoolCity;
    private String schoolState;
    private String schoolCountry;
    Long schoolPhone;
    Long schoolZip;
    private String image;
    private String status;
}
