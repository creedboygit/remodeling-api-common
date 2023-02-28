package com.hanssem.remodeling.common.common.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class MdcFilter extends OncePerRequestFilter {

    public static final String CONST_X_B3_TRACE_ID = "X-B3-TraceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        MDC.put(MdcType.CUST_NO.name(), Optional.ofNullable(request.getHeader("custNo")).orElse("-"));
        MDC.put(MdcType.REQUEST_URL.name(), Optional.of(request.getRequestURL().toString()).orElse(""));
        MDC.put(MdcType.TRACE_ID.name(), Optional.ofNullable(request.getHeader(CONST_X_B3_TRACE_ID)).orElse(""));
        filterChain.doFilter(request, response);
        MDC.clear();
    }

    public enum MdcType {
        TRACE_ID, REQUEST_URL, CUST_NO
    }
}
