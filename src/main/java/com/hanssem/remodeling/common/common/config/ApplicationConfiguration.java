package com.hanssem.remodeling.common.common.config;

import com.hanssem.remodeling.common.common.model.AdminEventInfo;
import com.hanssem.remodeling.common.common.model.AuthClaim;
import java.nio.charset.StandardCharsets;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class ApplicationConfiguration {
    @Bean
    InetAddress inetAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    /**
     * Request 단위로 Header의 인증정보를 담아줄 빈객체 생성
     * @return @AuthClaim
     */
    @Bean
    @RequestScope
    AuthClaim authClaim() {
        return new AuthClaim();
    }

    @Bean
    @RequestScope
    public AdminEventInfo adminEventInfo() {
        return new AdminEventInfo();
    }

    @Bean
    MessageSource messageSource() {
        val messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:messages/message");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }
}
