package com.paperScan.v1.service;

import com.eastedu.bigdata.remoteliving.pojo.dto.ClassIndicatorDTO;
import com.eastedu.bigdata.remoteliving.pojo.dto.UserOnlineDetailDTO;
import com.eastedu.bigdata.remoteliving.pojo.vo.UserOnlineCondition;

import java.util.List;

/**
 * Description:
 * DATE：2019/5/16 13:10
 * @author xiaobing
 */
public interface RemoteTeachingService {
    /**
     * 互联网实时在线人数
     * @param userOnlineCondition
     * @return
     */
    List<UserOnlineDetailDTO> getUserOnlineDetail(UserOnlineCondition userOnlineCondition, String section);

    /**
     * 直播上课指标
     * @param userOnlineCondition
     * @return
     */
    ClassIndicatorDTO realTimeOnlineClassIndicator(UserOnlineCondition userOnlineCondition, String section);

    /**
     * 直播备课指标
     * @param userOnlineCondition
     * @return
     */
    ClassIndicatorDTO realTimeOnlinePreClassIndicator(UserOnlineCondition userOnlineCondition, String section);
}
