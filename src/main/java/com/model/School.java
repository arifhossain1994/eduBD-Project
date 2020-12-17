package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends BaseModel{
    private String school_name, school_email, school_street, school_house,
             school_city, school_state, school_country;
    Long school_phone,school_zip;

    private String image, status;
}
