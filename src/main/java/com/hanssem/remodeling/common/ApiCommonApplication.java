package com.hanssem.remodeling.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@ServletComponentScan
@EnableCaching
public class ApiCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCommonApplication.class, args);
    }

}
