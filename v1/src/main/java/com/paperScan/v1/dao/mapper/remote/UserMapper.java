package com.paperScan.v1.dao.mapper.remote;

import com.paperScan.v1.pojo.dto.UserInfoDTO;
import com.paperScan.v1.pojo.po.UserInfoPO;
import com.paperScan.v1.pojo.vo.UserSignUpVO;
import com.paperScan.v1.pojo.vo.UserUpdateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:查询直播数据库
 * DATE：2019/5/15 10:08
 * @author xiaobing
 */
@Mapper
public interface UserMapper {
    /**
     * 用户注册
     * @param userSignUpVO
     * @return
     */
    String signUpUser(UserSignUpVO userSignUpVO);

    /**
     * 用户更新
     * @param userUpdateVO
     * @return
     */
    int userUpdate(UserUpdateVO userUpdateVO);

    /**
     * 用户失效
     * @param userUpdateVO
     * @return
     */
    int userDisable(UserUpdateVO userUpdateVO);

    /**
     * 用户删除
     * @param userUpdateVO
     * @return
     */
    int userDelete(UserUpdateVO userUpdateVO);

    /**
     * 判断用户是否存在，允许登录
     * @param userid
     * @return
     */
    int userSignIn(String userid);

    /**
     * 用户签到
     * @param userid
     * @return
     */
    int userSign(String userid);

    /**
     * 判断用户是否今天已经签到
     * @param userid
     * @return
     */
    int userHasSign(String userid);

    /**
     * 用户签到成功更新积分
     * @param userid
     * @return
     */
    int userSignAddCore(String userid);

    /**
     * 获取用户详情
     * @param userid
     * @return
     */
    UserInfoDTO userInfo(String userid);

    /**
     * 获取所有用户详情
     * @param
     * @return
     */
    List<UserInfoDTO> allUserInfo();

}
