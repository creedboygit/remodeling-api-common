package com.hanssem.remodeling.common.api.controller.common.request;

import com.hanssem.remodeling.common.constant.ScrapTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "BaseScrapRequest", description = "스크랩 기본 요청 정보")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BaseScrapRequest {

    @NotNull(message = "{message.default}")
    @Schema(name = "scrapTypeCode", title = "스크랩 코드", required = true, example = "SHOP", description = """
        스크랩 코드
        STORE_GOODS = 스토어상품, REMODELING_GOODS = 리모델링상품, CONSTRUCTION_CASE = 시공사례, IMAGE = 사진, MAGAZINE = 매거진, SHOP = 매장  
        """)
    private ScrapTypeCode scrapTypeCode;
}
