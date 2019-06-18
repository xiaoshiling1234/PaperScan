package com.paperScan.v1.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * DATE：2019/5/15 10:18
 * @author xiaobing
 */
@Data
@ApiModel
@AllArgsConstructor
public class UserUpdateVO {
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
    @ApiModelProperty(value = "用户分数,后端管理才能修改分数", example = "0")
    private Integer authrioty_score;
    @ApiModelProperty(value = "修改方式 (d删除 m逻辑删除 只需要传入userid），u更新，", example = "(d删除 m逻辑删除），则只需要传入userid，u更新，",required = true)
    private String modify_type;

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getModify_type() {
        return modify_type;
    }
}

