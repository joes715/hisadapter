package com.hospital.adapt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = {"com.hospital.adapt.mapper"})
public class HisAdaptApplication {
    private static String[] args = null;
    private static ConfigurableApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(HisAdaptApplication.class, args);
    }

}
