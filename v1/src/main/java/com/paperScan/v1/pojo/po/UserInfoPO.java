package com.paperScan.v1.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/17 16:04
 * @author xiaobing
 */
@Data
public class UserInfoPO {
    private String userid;
    private String password;
    private String username;
    private String phone;
    private int authrioty_score;
    private Boolean is_available;
    private Date create_time;
}
