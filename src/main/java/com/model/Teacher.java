package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher extends BaseModel{
    private Long schoolId;
    private Long teacherPhone;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;
    private String role;
    private String image;

}
