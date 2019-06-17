package com.paperScan.v1.controller;

import com.eastedu.bigdata.remoteliving.pojo.dto.ClassIndicatorDTO;
import com.eastedu.bigdata.remoteliving.pojo.dto.UserOnlineDetailDTO;
import com.eastedu.bigdata.remoteliving.pojo.vo.UserOnlineCondition;
import com.eastedu.bigdata.remoteliving.service.RemoteTeachingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xb on 2017/8/13
 *
 * @author xb
 * @version 1.0.0
 */
@Api(value = "在线阅读 API", description = "在线阅读")
@RestController
@RequestMapping("/remoteTeaching")
public class RemoteTeachingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteTeachingController.class);
    @Autowired
    private RemoteTeachingService remoteTeachingService;

    @ApiOperation(value = "高中远程教学实时在线用户", nickname = "realTimeOnlineUsers", response = UserOnlineCondition.class)
    @RequestMapping(value = "/GZ/realTimeOnlineUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> gzRealTimeOnlineUsers(@Validated UserOnlineCondition userOnlineCondition) {
        LOGGER.info("返回学生上课详情列表，参数{}", userOnlineCondition);
        List<UserOnlineDetailDTO> questionQueryDetailDTOS = remoteTeachingService.getUserOnlineDetail(userOnlineCondition,"GZ");
        return new ResponseEntity<>(questionQueryDetailDTOS, HttpStatus.OK);
    }
}

