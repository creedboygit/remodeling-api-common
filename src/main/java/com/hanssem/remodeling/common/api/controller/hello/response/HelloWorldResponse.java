package com.hanssem.remodeling.common.api.controller.hello.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "HelloWorldResponse", description = "시스템 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class HelloWorldResponse implements Serializable {

    @Schema(name = "hostName", title = "Host Name", type = "String", required = true, example = "hotname-778bfcb44b-sbww2")
    private String hostName;

    @Schema(name = "hostIp", title = "Host IP Address", type = "String", required = true, example = "172.16.143.23")
    private String hostIp;
    
    @Schema(name = "userName", title = "UserName By Claim Info", type = "String", required = true, example = "tester")
    private String userName;

    @Schema(name = "requestAt", title = "Request Time", type = "String", required = true, example = "2021-07-19 15:37:11")
    private String requestAt;

    public static HelloWorldResponse of(final String hostName, final String hostIp, final String userName, final String requestAt) {
        return new HelloWorldResponse(hostName, hostIp, userName, requestAt);
    }
}
