package com.paperScan.v1.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ScrollImageUpVOWithUrl {
    @ApiModelProperty(value = "图片顺序",required = true, example = "1")
    private Integer order;
    @ApiModelProperty(value = "跳转链接",required = true, example = "www.baidu.com")
    private String jumpUrl;
    @ApiModelProperty(value = "存储url", example = "50")
    private String url ;
}
