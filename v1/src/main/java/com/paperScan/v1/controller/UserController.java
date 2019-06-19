package com.paperScan.v1.controller;

import com.paperScan.v1.pojo.dto.MessageDTO;
import com.paperScan.v1.pojo.dto.UserInfoDTO;
import com.paperScan.v1.pojo.vo.UserSignUpVO;
import com.paperScan.v1.pojo.vo.UserUpdateVO;
import com.paperScan.v1.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xb
 *
 * @author xb
 * @version 1.0.0
 */
@Api(value = "用户操作 API")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", nickname = "userSignUp", response = String.class)
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> sighUp(@Validated UserSignUpVO userSignUpVO) {
        LOGGER.info("用户注册，参数{}", userSignUpVO);
        try {
            String userid = userService.signUpUser(userSignUpVO);
            return new ResponseEntity<>(new MessageDTO("账号注册成功："+userid), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageDTO("账号已存在"), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "用户登录", nickname = "userSignIn", response = String.class)
    @RequestMapping(value = "/userSignIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> sighUp(@RequestParam(value="userid") String userid) {
        LOGGER.info("用户注册，参数{}", userid);
        Boolean hasAccount = userService.userSignIn(userid);
        return new ResponseEntity<>(hasAccount, HttpStatus.OK);
    }

    @ApiOperation(value = "用户更新", nickname = "userUpdate", response = Boolean.class)
    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> userUpDate(@Validated UserUpdateVO userUpdateVO) {
        LOGGER.info("用户注册，参数{}", userUpdateVO);
        String message = userService.userUpdate(userUpdateVO);
        return new ResponseEntity<>(new MessageDTO(message), HttpStatus.OK);
    }

    @ApiOperation(value = "获取用户是否签到", nickname = "userHasSign", response = Boolean.class)
    @RequestMapping(value = "/userHasSign", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> userHasSign(@RequestParam(value="userid") String userid) {
        LOGGER.info("用户注册，参数{}", userid);
        Boolean hasSign = userService.userHasSign(userid);
        return new ResponseEntity<>(hasSign, HttpStatus.OK);
    }

    @ApiOperation(value = "用户签到", nickname = "userSign", response = Boolean.class)
    @RequestMapping(value = "/userSign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<?> userSign(@RequestParam(value="userid") String userid) {
        LOGGER.info("用户注册，参数{}", userid);
        String signInfo = userService.userSign(userid);
        System.out.println(signInfo);
        return new ResponseEntity<>(new MessageDTO(signInfo), HttpStatus.OK);
    }

    @ApiOperation(value = "获取用户详细信息", nickname = "userInfo", response = UserInfoDTO.class)
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> userInfo(@RequestParam(value="userid") String userid) {
        LOGGER.info("用户注册，参数{}", userid);
        UserInfoDTO userInfo = userService.userInfo(userid);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    @ApiOperation(value = "获取所有用户详细信息", nickname = "allUserInfo", response = UserInfoDTO.class)
    @RequestMapping(value = "/allUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> allUserInfo() {
        List<UserInfoDTO> userInfo = userService.allUserInfo();
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
//    @ApiOperation(value = "高中远程教学实时在线用户", nickname = "realTimeOnlineUsers", response = UserInfoPO.class)
//    @RequestMapping(value = "/GZ/realTimeOnlineUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public HttpEntity<?> gzRealTimeOnlineUsers(@Validated UserOnlineCondition userOnlineCondition) {
//        LOGGER.info("返回学生上课详情列表，参数{}", userOnlineCondition);
//        List<UserOnlineDetailDTO> questionQueryDetailDTOS = remoteTeachingService.getUserOnlineDetail(userOnlineCondition,"GZ");
//        return new ResponseEntity<>(questionQueryDetailDTOS, HttpStatus.OK);
//    }
}

