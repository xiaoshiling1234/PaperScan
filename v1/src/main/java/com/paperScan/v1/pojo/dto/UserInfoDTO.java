package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/16 13:42
 */

@Data
@ApiModel
@AllArgsConstructor
public class UserInfoDTO {
    @ApiModelProperty(value = "用户账号")
    private String userid;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户名称")
    private String username;
    @ApiModelProperty(value = "用户电话")
    private String phone;
    @ApiModelProperty(value = "用户权限分数")
    private int authrioty_score;
    @ApiModelProperty(value = "是否可用")
    private Boolean is_available;
    @ApiModelProperty(value = "创建时间")
    private Date create_time;
}
