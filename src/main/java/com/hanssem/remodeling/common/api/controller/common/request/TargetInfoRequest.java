package com.hanssem.remodeling.common.api.controller.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "TargetInfoRequest", description = "스크랩 대상 요청 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TargetInfoRequest {

    @Positive(message = "{message.positive}")
    @Schema(name = "id", title = "대상 아이디(고유값)", required = true, example = "1", description = "타켓 아이디(고유값)")
    private long id;
}
