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
/**
 * 注册
 */
public class UserSignUpVO {
    @ApiModelProperty(value = "用户账号", example = "xiaosdsd")
    private String userid;
    @ApiModelProperty(value = "用户密码", example = "123456")
    private String password;
    @ApiModelProperty(value = "用户名称", example = "张三")
    private String username;
    @ApiModelProperty(value = "用户电话", example = "13402021461")
    private String phone;
    @ApiModelProperty(value = "用户地址", example = "成都")
    private String address;
}

