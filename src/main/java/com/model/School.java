package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends BaseModel{
    private String schoolName;
    private String schoolEmail;
    private String schoolWebsite;
    private String schoolAddress1;
    private String schoolAddress2;
    private String schoolCity;
    private String schoolState;
    private String schoolCountry;
    Long schoolPhone;
    Long schoolZip;
    private String image;
    private String status;
}
