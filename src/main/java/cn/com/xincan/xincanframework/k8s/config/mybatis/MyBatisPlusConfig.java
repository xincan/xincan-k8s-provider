package cn.com.xincan.xincanframework.k8s.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;




/**
 * copyright (C), 2019, 心灿基础架构
 *
 * @program xincan-framework
 * MyBatisPlus 配置
 * @author Jiangxincan
 * @create 2019/12/20 15:27
 * @version 0.0.1
 * <author>                <time>                  <version>                   <description>
 * Jiangxincan         2019/12/20 15:27             0.0.1                         MyBatisPlus 配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.com.xincan.xincanframework.k8s.server.mapper")
public class MyBatisPlusConfig {

    private static final String MAPPER_PATTERN = "classpath:mapper/*.xml";

    @Resource
    private HikariDataSource hikariDataSource;


    /**
     * MyBatisPlus 配置分页
     * @author Jiangxincan
     * @date 2020/1/7 10:22
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
