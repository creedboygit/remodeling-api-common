package com.hanssem.remodeling.common.api.controller.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "AddScrapRequest", description = "스크랩 등록")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddScrapRequest extends BaseScrapRequest{

    @Valid
    @NotNull(message = "{message.default}")
    @Schema(name = "target", title = "대상 정보", required = true, description = "대상 정보")
    private AddScrapRequest.AddTargetInfoRequest target;

    @Getter
    @Schema(name = "DeleteTargetInfoRequest", description = "대상 정보")
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AddTargetInfoRequest {

        @Positive(message = "{message.positive}")
        @Schema(name = "id", title = "대상 아이디(고유값)", required = true, example = "1", description = "타켓 아이디(고유값)")
        private long id;

        @NotBlank(message = "{message.default}")
        @Schema(name = "name", title = "대상명", required = true, example = "한샘 디자인파크 고양스타필드점", description = "대상명(ex: 상품명, 매장명)")
        private String name;

        @NotBlank(message = "{message.default}")
        @Schema(name = "landingUrl", title = "대상 랜딩 url", required = true, example = "https://dev-m.remodeling.hanssem.com/shop/804", description = "대상 랜딩 url")
        private String landingUrl;

        @NotBlank(message = "{message.default}")
        @Schema(name = "imageUrl", title = "대상 이미지 full url", required = true, example = "https://devimage.hanssem.com/hsimg/shop/804/main/Ugm8Yh0xatOS3hcb.jpg", description = "대상 이미지 full url")
        private String imageUrl;
    }
}
