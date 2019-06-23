package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class ScrollImaPathListDTO {
    @ApiModelProperty(value = "picture_order")
    private Integer picture_order;
    @ApiModelProperty(value = "img_path")
    private String img_path;
    @ApiModelProperty(value = "jumpurl")
    private String jumpurl;
}
