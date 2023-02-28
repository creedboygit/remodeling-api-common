package com.hanssem.remodeling.common.common.filter;


import com.hanssem.remodeling.common.common.model.AuthClaim;
import com.hanssem.remodeling.common.common.response.Response;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {


    @Lazy
    private final ObjectMapper mapper;

    @Lazy
    private final AuthClaim authClaim;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            String custNo = request.getHeader("custNo");
            String intMbsCustNo = request.getHeader("intMbsCustNo");
            String accessToken = request.getHeader("Authorization");

            if (custNo != null) {
                try {
                    authClaim.setCustNo(Long.parseLong(custNo));
                } catch (NumberFormatException e) {
                    authClaim.setCustNo(Float.valueOf(custNo).intValue());
                }
            }
            if (intMbsCustNo != null) {
                try {
                    authClaim.setIntMbsCustNo(Long.parseLong(intMbsCustNo));
                } catch (NumberFormatException e) {
                    authClaim.setIntMbsCustNo(Float.valueOf(intMbsCustNo).longValue());
                }
            }

            authClaim.setAccessToken(accessToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            displayException(response);
        }
    }

    /**
     * Filter에서 오류 발생 시 에러 화면 출력 용
     * @param response 응답
     * @throws IOException 발생
     */
    private void displayException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(new Response<>(
                ResponseCode.ERROR.getCode(),
                ResponseCode.ERROR.getMessage(),
                null)));
        response.flushBuffer();
    }
}
