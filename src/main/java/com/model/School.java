package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends BaseModel{
    private String school_name, school_email, school_street, school_house,
            school_zip, school_city, school_state, school_country;

    private String image, status;
}
