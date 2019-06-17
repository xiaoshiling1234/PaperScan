package com.paperScan.v1.service.impl;

import com.paperScan.v1.dao.mapper.remote.UserMapper;
import com.paperScan.v1.pojo.dto.UserInfoDTO;
import com.paperScan.v1.pojo.vo.UserSignUpVO;
import com.paperScan.v1.pojo.vo.UserUpdateVO;
import com.paperScan.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * DATE：2019/5/16 13:10
 *
 * @author xiaobing
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String signUpUser(UserSignUpVO userSignUpVO) {
        return userMapper.signUpUser(userSignUpVO);
    }

    @Override
    public int userUpdate(UserUpdateVO userUpdateVO) {
        int line = 0;
        if (userUpdateVO.getModify_type() == "d") {
            line = userMapper.userDelete(userUpdateVO);
        } else if (userUpdateVO.getModify_type() == "m") {
            line = userMapper.userDisable(userUpdateVO);
        } else if (userUpdateVO.getModify_type() == "u") {
            line = userMapper.userUpdate(userUpdateVO);
        }
        return line;
    }

    @Override
    /**
     * 返回值>0则表示注册过
     */
    public Boolean userSignIn(String userid) {
        return userMapper.userSignIn(userid) > 0 ? true : false;
    }

    @Override
    /**
     * 返回值1>0则表示签到成功 返回值2>0表示积分更新成功 同时成功返回true
     */
    public Boolean userSign(String userid) {
        int signIn = userMapper.userSignIn(userid);
        if (signIn > 0) {
            int signAddCore = userMapper.userSignAddCore(userid);
            if (signAddCore > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean userHasSign(String userid) {
        return userMapper.userHasSign(userid) > 0 ? true : false;
    }

    @Override
    public UserInfoDTO userInfo(String userid) {
        return userMapper.userInfo(userid);
    }

    @Override
    public List<UserInfoDTO> allUserInfo() {
        return userMapper.allUserInfo();
    }

}
