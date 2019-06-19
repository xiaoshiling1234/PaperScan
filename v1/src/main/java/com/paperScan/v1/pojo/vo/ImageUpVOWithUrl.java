package com.paperScan.v1.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel
public class ImageUpVOWithUrl {
    @ApiModelProperty(value = "文档标题",required = true, example = "林志玲没屁儿")
    private String title;
    @ApiModelProperty(value = "浏览所需积分",required = true, example = "50")
    private Integer authrioty_score;
    @ApiModelProperty(value = "资源层级",required = true, example = "50")
    private Integer resource_level;
    @ApiModelProperty(value = "资源处于该层级顺序",required = true, example = "50")
    private Integer resource_order ;
    @ApiModelProperty(value = "存储url", example = "50")
    private String url ;

}
