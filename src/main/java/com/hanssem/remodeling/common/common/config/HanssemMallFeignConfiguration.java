package com.hanssem.remodeling.common.common.config;

import com.hanssem.remodeling.common.common.model.AuthClaim;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

@Slf4j
@RequiredArgsConstructor
public class HanssemMallFeignConfiguration {

    private static final String AUTHORIZATION = "Authorization";
    private final AuthClaim authClaim;

    /**
     * RequestParam 에서 LocalDate, LocalDateTime, LocalTime 을 사용을 할 때 ISO formatter 로 보내기 위한 설정
     *
     * @see org.springframework.web.bind.annotation.RequestParam
     */
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info("[bean] feign header configuration");
        return requestTemplate -> {
            requestTemplate.header(AUTHORIZATION, authClaim.getAccessToken());
        };
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            log.error("API call error -> {} : {}", response.status(), response.body());
            return new Exception();
        };
    }
}