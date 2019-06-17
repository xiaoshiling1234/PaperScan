package com.paperScan.v1.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * DATE：2019/5/15 10:18
 * @author xiaobing
 */
@Data
@ApiModel
public class UserOnlineCondition {
    @ApiModelProperty(value = "学校名称", example = "0")
    private String schoolName;
    @ApiModelProperty(value = "学届code", example = "0")
    private String seniorCode;
//    @ApiModelProperty(value = "学届名称", example = "0")
//    private String seniorName;
    @ApiModelProperty(value = "文理", example = "文科")
    private String wlk;
    @ApiModelProperty(value = "学科", example = "YW")
    private String subjectCode;
    @ApiModelProperty(value = "省", example = "510000")
    private String provinceAdCode;
    @ApiModelProperty(value = "市", example = "510100")
    private String cityAdCode;
    @ApiModelProperty(value = "区", example = "510102")
    private String areaAdCode;
}

