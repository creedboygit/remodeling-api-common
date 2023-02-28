package com.hanssem.remodeling.common.api.controller.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "DeleteScrapRequest", description = "스크랩 삭제 요청 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteScrapRequest extends BaseScrapRequest {

    @Positive(message = "{message.positive}")
    @Schema(name = "targetId", title = "대상 아이디(고유값)", required = true, example = "1", description = "타켓 아이디(고유값)")
    private long targetId;
}
