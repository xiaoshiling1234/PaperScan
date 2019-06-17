package com.paperScan.v1.common.conf;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/7 13:18
 */

public class KafkaDefaultConfig {
    public final static String bootstrapServers="eastedu-master-01:9092, eastedu-slave-01:9092, eastedu-slave-02:9092";
    public final static String groupId="test17";
    public final static String enableAutoCommit="false";
    public final static String autoCommitIntervalMs="1000";
    public final static String sessionTimeoutMs="30000";
    public final static String keyDeserializer="org.apache.kafka.common.serialization.StringDeserializer";
    public final static String valueDeserializer="org.apache.kafka.common.serialization.StringDeserializer";
    public final static String autoOffsetReset="earliest";
    // 用户离线最大心跳间隔
    public final static int maxHeartWaitTime=30;
}
