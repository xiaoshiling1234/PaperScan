package com.paperScan.v1.dao.mapper.remote;

import com.eastedu.bigdata.remoteliving.pojo.po.UserOnlineDetailPO;
import com.eastedu.bigdata.remoteliving.pojo.vo.UserOnlineCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:查询直播数据库
 * DATE：2019/5/15 10:08
 * @author xiaobing
 */
@Mapper
public interface RemoteTachingMapper {
    /**
     * 获取学生在线列表详情
     * @param userOnlineCondition
     * @return
     */
    List<UserOnlineDetailPO> gzGetUserOnlineDetail(UserOnlineCondition userOnlineCondition);

    /**
     * 获取学生在线列表详情
     * @param userOnlineCondition
     * @return
     */
    List<UserOnlineDetailPO> czGetUserOnlineDetail(UserOnlineCondition userOnlineCondition);

    /**
     * 获取学生在线列表详情
     * @param userOnlineCondition
     * @return
     */
    List<UserOnlineDetailPO> xxGetUserOnlineDetail(UserOnlineCondition userOnlineCondition);
}
