package com.hanssem.remodeling.common.api.controller.hello;

import com.hanssem.remodeling.common.api.controller.hello.request.HelloRequest;
import com.hanssem.remodeling.common.api.controller.hello.response.HelloWorldResponse;
import com.hanssem.remodeling.common.api.service.hello.HelloService;
import com.hanssem.remodeling.common.common.model.AuthClaim;
import com.hanssem.remodeling.common.common.response.PageResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Tag(name = "Hello", description = "the hello API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/hello")
public class HelloController {

    private final InetAddress inetAddress;
    private final HelloService helloService;
    
    /**
     * Header 정보가 담겨 있는 객체입니다.
     */
    private final AuthClaim authClaim;

    @Operation(summary = "정보", description = "시스템 정보", tags = {"Hello"})
    @ApiResponse(content = @Content(schema = @Schema(implementation = HelloWorldResponse.class)))
    @GetMapping(value = "/world")
    public HelloWorldResponse world(@RequestHeader HttpHeaders headers) {
        return HelloWorldResponse.of(inetAddress.getHostName(), inetAddress.getHostAddress(), "tester",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Operation(summary = "페이지네이션", description = "첫번째 인자는 페이지 및 소팅, 두번째 인자는 페이지네이션", responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = PageResponse.class)))})
    @GetMapping(value = "/page")
    public PageResponse page(HelloRequest paginationRequest) {
        log.debug(paginationRequest.toString());
        return PageResponse.emptyResponse();
    }

    @Operation(summary = "레디스 테스트 - cacheable", description = "레디스 테스트 cacheable")
    @GetMapping("/test-redis")
    public String testCacheable(final String param) {
        return helloService.testCacheable(param);
    }

    @Operation(summary = "레디스 테스트 - evict all", description = "레디스 테스트 evict all")
    @DeleteMapping("/test-redis")
    public void testCacheEvictAll() {
        helloService.testCacheEvictAll();
    }
}
