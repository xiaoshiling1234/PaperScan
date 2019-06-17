package com.paperScan.v1.common.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/20 13:45
 */
@Configuration
@MapperScan(basePackages = "com.eastedu.bigdata.remoteliving.dao.mapper.informationPlatform", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class InformationSourceConfig {
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second") // application.properteis中对应属性的前缀
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.eastedu.bigdata.remoteliving.pojo");
        final String MAPPER_LOCATION="classpath:mybatis/mapper/*.xml";
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                MAPPER_LOCATION
        ));

        return bean.getObject();
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager getTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
