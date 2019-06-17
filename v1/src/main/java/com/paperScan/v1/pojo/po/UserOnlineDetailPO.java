package com.paperScan.v1.pojo.po;

import lombok.Data;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/17 16:04
 * @author xiaobing
 */
@Data
public class UserOnlineDetailPO {
    private String userId;
    private String userName;
    private int channelId;
    private String channelName;
    private String seniorCode;
    private String seniorName;
    private String wlk;
    private String provinceAdCode;
    private String provinceName;
    private String cityAdCode;
    private String cityName;
    private String areaAdCode;
    private String areaName;
    private String schoolName;
    private double schoolLat;
    private double schoolLng;
    private String subjectCode;
    private String subjectName;
    private String beginTime;
    private String endTime;
//    private Date classDate;
    private String remoteType;
    private int satelliteOnline;
}
