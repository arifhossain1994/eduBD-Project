package com.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class Admin extends BaseModel{
    private Long schoolId;
    private Long adminPhone;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;
    private String role;
    private String image;

    @Override
    public boolean equals(Object object) {
        return object instanceof Admin && (this == object || EqualsBuilder.reflectionEquals(this, object));
    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
