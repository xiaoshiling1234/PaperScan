package com.paperScan.v1.service;


import com.paperScan.v1.pojo.dto.UserInfoDTO;
import com.paperScan.v1.pojo.vo.UserSignUpVO;
import com.paperScan.v1.pojo.vo.UserUpdateVO;

import java.util.List;

/**
 * Description:
 * DATE：2019/5/16 13:10
 * @author xiaobing
 */
public interface UserService {
    /**
     * 用户注册
     * @param userSignUpVO
     * @return
     */
    String signUpUser(UserSignUpVO userSignUpVO);

    int userUpdate(UserUpdateVO userUpdateVO);

    Boolean userSignIn(String userid);

    Boolean userSign(String userid);

    Boolean userHasSign(String userid);

    UserInfoDTO userInfo(String userid);

    List<UserInfoDTO> allUserInfo();
}
