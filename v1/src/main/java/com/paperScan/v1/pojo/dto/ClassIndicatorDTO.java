package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/16 13:42
 */

@Data
@ApiModel
@AllArgsConstructor
public class ClassIndicatorDTO {
    @ApiModelProperty(value = "应上线人数")
    private int needOnlinePersons;
    @ApiModelProperty(value = "卫星网上线人数")
    private int satelliteOnlinePersons;
    @ApiModelProperty(value = "互联网上线人数")
    private int interNetOnline;
    @ApiModelProperty(value = "互联网与卫星网并集上线人数")
    private int mergeOnlinePersons;
    @ApiModelProperty(value = "互联网与卫星网并集上线人明细")
    private ArrayList<UserOnlineDetailDTO> mergeOnlinePersonsDetail;
    @ApiModelProperty(value = "应上线而未上线人数")
    private int needOnlinePersonsButNot;
    @ApiModelProperty(value = "应上线而未上线用户明细")
    private ArrayList<UserOnlineDetailDTO> needOnlinePersonsButNotDetail;
    @ApiModelProperty(value = "实时上线率")
    private double realOnlineRate;
}
