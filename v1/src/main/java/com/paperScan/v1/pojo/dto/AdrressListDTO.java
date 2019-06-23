package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class AdrressListDTO {
    @ApiModelProperty(value = "friendname")
    private String friendname;
    @ApiModelProperty(value = "friendphone")
    private String friendphone;
}
