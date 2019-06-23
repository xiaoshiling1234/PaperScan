package com.paperScan.v1.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRelationVO {
    @ApiModelProperty(value = "用户账号", example = "xiaosdsd")
    private String userid;
    @ApiModelProperty(value = "好友名称", example = "张三")
    private String friendname;
    @ApiModelProperty(value = "好友电话", example = "13402021461")
    private String friendphone;
}
