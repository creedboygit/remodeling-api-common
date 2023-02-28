package com.hanssem.remodeling.common.api.controller.commoncode.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(title = "CommoncodeResponse", description = "공통코드 그룹코드 응답객체")
public class CommoncodeResponse {

    @Schema(title = "codeId", description = "공통코드 그룹 코드", example = "")
    private String codeId;

    @Schema(title = "upperCodeId", description = "공통코드 상위 그룹코드", example = "")
    private String upperCodeId;

    @Schema(title = "codeName", description = "공통코드 그룹 코드명", example = "'상품뱃지코드'")
    private String codeName;

    @Schema(title = "codeKorName", description = "공통코드 그룹 한글명", example = "GOODS_BADGE_CODE")
    private String codeKorName;

}
