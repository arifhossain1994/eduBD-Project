package com.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseModel {
    Long id, created_by, updated_by;
    @ApiModelProperty(hidden = true)
    private LocalDateTime created_date;
    @ApiModelProperty(hidden = true)
    private LocalDateTime updated_date;
}
