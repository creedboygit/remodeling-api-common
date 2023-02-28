package com.hanssem.remodeling.common.api.controller.commoncode.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommoncodeSearchRequest {
    @Schema(title = "codeId", type = "string", example = "8F9787D24F4A11ED9DDCE68E18CDBD49")
    private String codeId;

    @Schema(title = "codeName", type = "string", example = "GOODS_BADGE_CODE")
    private String codeName;
}
