package com.paperScan.v1.common.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Description:
 * DATE：2019/5/8 14:15
 * @author xiaobing
 */
public class EnvConfReader {

    public static Properties readProperties(String path) throws IOException {
        Properties pro = new Properties();
        FileInputStream in = new FileInputStream(path);
        pro.load(in);
        return pro;
    }

    public Properties readEnvProperties() throws IOException {
        String classDir=this.getClass().getClassLoader().getResource("").toString().substring(6);
        Properties rootProperties = readProperties(classDir+"application.properties");
        Object envName = rootProperties.getOrDefault("spring.profiles.active", "dev");
        String envPath=classDir+"application-"+envName+".properties";
        Properties properties = readProperties(envPath);
        return properties;
    }

    public static void main(String[] args) throws IOException {
        EnvConfReader envReaderUtils = new EnvConfReader();
        Properties properties = envReaderUtils.readEnvProperties();
        System.out.println(properties.get("datasource.msyql.URL"));

    }
}
