package com.paperScan.v1.service.impl;

import com.eastedu.bigdata.remoteliving.common.conf.KafkaDefaultConfig;
import com.eastedu.bigdata.remoteliving.dao.mapper.remote.RemoteTachingMapper;
import com.eastedu.bigdata.remoteliving.kafka.UnitedClassProcess;
import com.eastedu.bigdata.remoteliving.kafka.unitedClass.UnitedClassObserver;
import com.eastedu.bigdata.remoteliving.pojo.dto.ClassIndicatorDTO;
import com.eastedu.bigdata.remoteliving.pojo.dto.UserOnlineDetailDTO;
import com.eastedu.bigdata.remoteliving.pojo.po.UserOnlineDetailPO;
import com.eastedu.bigdata.remoteliving.pojo.vo.UserOnlineCondition;
import com.eastedu.bigdata.remoteliving.service.RemoteTeachingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.eastedu.bigdata.remoteliving.common.utils.DateUtils.*;

/**
 * Description:
 * DATE：2019/5/16 13:10
 * @author xiaobing
 */
@Slf4j
@Service
public class RemoteTeachingServiceImpl implements RemoteTeachingService {
    private RemoteTachingMapper remoteTachingMapper;
    private UnitedClassProcess unitedClassProcess;
    @Autowired
    public RemoteTeachingServiceImpl(RemoteTachingMapper remoteTachingMapper,UnitedClassProcess unitedClassProcess) {
        this.remoteTachingMapper = remoteTachingMapper;
        this.unitedClassProcess=unitedClassProcess;
        //启动日志解析实时进程
        new Thread(this.unitedClassProcess,"remoteLiveOnlineParseKafka").start();
    }
    @Override
    public List<UserOnlineDetailDTO> getUserOnlineDetail(UserOnlineCondition userOnlineCondition,String section) {
        List<UserOnlineDetailDTO> userOnlineDetailDTOS = new ArrayList<>();
        List<UserOnlineDetailPO> userOnlineDetailPOS;
        switch(section){
            // 获取卫星网学生在线信息
            case "GZ" :
                userOnlineDetailPOS = remoteTachingMapper.gzGetUserOnlineDetail(userOnlineCondition);
                break; //可选
            case "CZ" :
                userOnlineDetailPOS = remoteTachingMapper.czGetUserOnlineDetail(userOnlineCondition);
                break; //可选
            case "XX":
                userOnlineDetailPOS = remoteTachingMapper.xxGetUserOnlineDetail(userOnlineCondition);
                break; //可选
            //你可以有任意数量的case语句
            default : //可选
                userOnlineDetailPOS = null;
                log.error("ERROR: Section need be given！");
        }

        // 获取互联网在线名单
        UnitedClassObserver unitedClassObserver = unitedClassProcess.getUnitedClassObserver();
        // 返回用户+最近一次心跳时间
        Map<String, String> nearstHeartBeatMap = unitedClassObserver.getNearstHeartBeatMap();
        log.info("用户总数:",String.valueOf(userOnlineDetailPOS.size()));
        log.info("用户最新在线时间:",nearstHeartBeatMap.toString());
        // 组装卫星网和互联网在线状态，addSeconds("2019-05-09 16:42:28",10,DATE_TIME_PATTERN)
        userOnlineDetailPOS.forEach(userOnlineDetailPO->{
            UserOnlineDetailDTO userOnlineDetailDTO=new UserOnlineDetailDTO();
            if(Objects.nonNull(userOnlineDetailPO.getSubjectCode())){
                userOnlineDetailPO.setSubjectCode(userOnlineDetailPO.getSubjectCode().trim());
            }
            if(Objects.nonNull(userOnlineDetailPO.getSubjectName())){
                userOnlineDetailPO.setSubjectName(userOnlineDetailPO.getSubjectName().trim());
            }
            BeanUtils.copyProperties(userOnlineDetailPO,userOnlineDetailDTO);
            String last_time=nearstHeartBeatMap.getOrDefault(userOnlineDetailPO.getUserId(),"1999-12-30 14:39:21");
            String now_time=getTimestampDate(timecurrentTime());
            // 计算最近心跳与当前间隔时间，如果小于设定时间阈值，则设置互联网在线
            int differSeconds;
            try {
                differSeconds = differSeconds(now_time, last_time);
            } catch (Exception e) {
                differSeconds=Integer.MAX_VALUE;
                log.error("哦豁:"+now_time+last_time+differSeconds);
                e.printStackTrace();
            }
            if (differSeconds<=KafkaDefaultConfig.maxHeartWaitTime){
                userOnlineDetailDTO.setInterNetOnline(1);
            }
            userOnlineDetailDTOS.add(userOnlineDetailDTO);
        });
        return userOnlineDetailDTOS;
    }

}
