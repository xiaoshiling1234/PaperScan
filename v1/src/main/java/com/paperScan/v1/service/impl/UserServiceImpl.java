package com.paperScan.v1.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paperScan.v1.dao.mapper.UserMapper;
import com.paperScan.v1.pojo.JsonBean;
import com.paperScan.v1.pojo.dto.AdrressListDTO;
import com.paperScan.v1.pojo.dto.UserInfoDTO;
import com.paperScan.v1.pojo.vo.UserRelationVO;
import com.paperScan.v1.pojo.vo.UserSignUpVO;
import com.paperScan.v1.pojo.vo.UserUpdateVO;
import com.paperScan.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        int result = userMapper.signUpUser(userSignUpVO);
        System.out.println(result);
        if (result > 0) {
            return userSignUpVO.getUserid();
        }
        return "faild";
    }

    @Override
    public String userUpdate(UserUpdateVO userUpdateVO) {
        int line = 0;
        System.out.println(userUpdateVO.getModify_type());
        if (userUpdateVO.getModify_type().equals("d")) {
            line = userMapper.userDelete(userUpdateVO);
            return line > 0 ? "删除成功" : "删除失败";
        } else if (userUpdateVO.getModify_type().equals("m")) {
            line = userMapper.userDisable(userUpdateVO);
            return line > 0 ? "成功设置失效" : "失败设置失效";
        } else if (userUpdateVO.getModify_type().equals("u")) {
            line = userMapper.userUpdate(userUpdateVO);
            return line > 0 ? "更新成功" : "更新失败";
        }
        return "修改方式传参错误";
    }

    @Override
    /**
     * 返回值>0则表示注册过
     */
    public Boolean userSignIn(String userid, String password) {
        int result = userMapper.userSignIn(userid, password);
        System.out.println(result);
        return result > 0 ? true : false;
    }

    @Override
    /**
     * 返回值1>0则表示签到成功 返回值2>0表示积分更新成功 同时成功返回true
     */
    public String userSign(String userid) {
        //判断签到过没有
        int has_signIn = userMapper.userHasSign(userid);
        if (has_signIn > 0) {
            return "已经签到过了，无法继续签到";
        } else {
            int signIn = userMapper.userSign(userid);
            System.out.println(signIn);
            if (signIn > 0) {
                int signAddCore = userMapper.userSignAddCore(userid);
                if (signAddCore > 0) {
                    return "签到成功,积分已更新";
                }
                return "签到成功，积分更新失败";
            }
        }
        return "Faild";
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

    @Override
    public Boolean adressUp(String adressUp) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<JsonBean>() {
        }.getType();
        JsonBean adressUpJsonBean = gson.fromJson(adressUp, type);
        List<UserRelationVO> info = new ArrayList<UserRelationVO>();
        for (JsonBean.data data : adressUpJsonBean.data) {
            info.add(new UserRelationVO(adressUpJsonBean.userid, data.name, data.phone));
        }
        int result = userMapper.adressUp(info);
        return result > 0 ? true : false;
    }

    @Override
    public List<AdrressListDTO> getAdressList(String userid) {
        List<AdrressListDTO> getAdressList= userMapper.getAdressList(userid);
        return getAdressList;
    }
}
