package com.hanssem.remodeling.common.common.config;

import com.hanssem.remodeling.common.common.filter.MdcFilter;
import com.hanssem.remodeling.common.common.filter.MdcFilter.MdcType;
import feign.RequestInterceptor;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignGlobalConfig {

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate ->
                requestTemplate.header(MdcFilter.CONST_X_B3_TRACE_ID, Optional.ofNullable(MDC.get(MdcType.TRACE_ID.name())).orElse(""));
    }

}