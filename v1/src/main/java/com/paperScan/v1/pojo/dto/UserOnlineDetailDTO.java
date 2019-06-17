package com.paperScan.v1.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/16 13:42
 */

@Data
@ApiModel
public class UserOnlineDetailDTO {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户Name")
    private String userName;
    @ApiModelProperty(value = "通道ID")
    private int channelId;
    @ApiModelProperty(value = "通道名称")
    private String channelName;
    @ApiModelProperty(value = "学届CODE")
    private String seniorCode;
    @ApiModelProperty(value = "学届NAME")
    private String seniorName;
    @ApiModelProperty(value = "文理科")
    private String wlk;
    @ApiModelProperty(value = "省ID")
    private String provinceAdCode;
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    @ApiModelProperty(value = "城市ID")
    private String cityAdCode;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    @ApiModelProperty(value = "区县ID")
    private String areaAdCode;
    @ApiModelProperty(value = "区县名称")
    private String areaName;
    @ApiModelProperty(value = "学校名称")
    private String schoolName;
    @ApiModelProperty(value = "学校经度")
    private double schoolLat;
    @ApiModelProperty(value = "学校纬度")
    private double schoolLng;
    @ApiModelProperty(value = "学科CODE")
    private String subjectCode;
    @ApiModelProperty(value = "学科NAME")
    private String subjectName;
    @ApiModelProperty(value = "课程开始时间")
    private String beginTime;
    @ApiModelProperty(value = "课程结束时间")
    private String endTime;
//    @ApiModelProperty(value = "课表时间")
//    private Date classDate;
    @ApiModelProperty(value = "直播或备课类型")
    private String remoteType;
    @ApiModelProperty(value = "卫星网是否在线")
    private int satelliteOnline;
    @ApiModelProperty(value = "互联网是否在线")
    private int interNetOnline;
}
