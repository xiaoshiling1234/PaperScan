package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class Top3AirticleDown {
    @ApiModelProperty(value = "title")
    private String title;
    @ApiModelProperty(value = "authrioty_score")
    private Integer authrioty_score;
    @ApiModelProperty(value = "img_path")
    private String img_path;
    @ApiModelProperty(value = "text")
    private String text;
    @ApiModelProperty(value = "resource_level")
    private Integer resource_level ;
    @ApiModelProperty(value = "resource_order")
    private Integer resource_order;
}
