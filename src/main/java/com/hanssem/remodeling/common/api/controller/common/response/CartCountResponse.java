package com.hanssem.remodeling.common.api.controller.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "CartCountResponse", description = "장바구니 갯수 조회 응답 객체 (한샘몰 API)")
public class CartCountResponse {

    @Schema(title = "cartCnt", description = "장바구니 갯수", example = "2")
    private int cartCnt;
}
