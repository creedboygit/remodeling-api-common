package com.hanssem.remodeling.common.common.interceptor;

import com.hanssem.remodeling.common.common.annotation.OnlyLogin;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.model.AuthClaim;
import com.hanssem.remodeling.common.constant.ResponseCode;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class OnlyLoginInterceptor implements HandlerInterceptor {

    private final AuthClaim authClaim;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isPass(request, handler)) {
            return true;
        }
        throw new CustomException(ResponseCode.ONLY_LOGIN);
    }

    private boolean isPass(final HttpServletRequest request, final Object handler) {
        if (request.getRequestURI().contains("swagger")) {
            return true;
        }

        final var method = (HandlerMethod) handler;
        if (Objects.isNull(method.getMethodAnnotation(OnlyLogin.class))) {
            return true;
        }

        return StringUtils.isNotBlank(authClaim.getAccessToken());
    }
}
