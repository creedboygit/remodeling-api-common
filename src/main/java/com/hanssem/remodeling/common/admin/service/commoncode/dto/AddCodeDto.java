package com.hanssem.remodeling.common.admin.service.commoncode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCodeDto {

    @Schema(title = "standardClassId", description = "표준분류코드", example = "3EA3F6F750624A1C9580E02EC4B052F9")
    private String standardClassId;

    @Schema(title = "codeTypeCode", description = "코드유형코드", example = "1")
    private String codeTypeCode;

    @Schema(title = "codeName", description = "코드명", example = "GOODS_STATE_CODE")
    private String codeName;

    @Schema(title = "codeKorName", description = "코드한글명", example = "상품상태코드")
    private String codeKorName;

    @Schema(title = "codeDescription", description = "설명", example = "")
    private String codeDescription;

    @Schema(title = "upperCodeName", description = "상위코드", example = "")
    private String upperCodeName;

    @Schema(title = "validEndDatetime", description = "유효종료일시", example = "2999-12-31T12:00:00")
    private LocalDateTime validEndDatetime;

}
