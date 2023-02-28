package com.hanssem.remodeling.common.api.service.commoncode.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommoncodeAndValueDto {

    /**
     * Commoncode
     */
    private String codeId;

    private String codeName;

    private String codeKorName;

    private String upperCodeId;

    /**
     * CommoncodeValue
     */
    private String codeValueId;

    private String codeValue;

    private String codeValueName;

    private int codeValueSeq;

    public CommoncodeAndValueDto(String codeId, String codeName, String codeKorName, String upperCodeId,
        String codeValueId, String codeValue, String codeValueName, int codeValueSeq) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.codeKorName = codeKorName;
        this.upperCodeId = upperCodeId;
        this.codeValueId = codeValueId;
        this.codeValue = codeValue;
        this.codeValueName = codeValueName;
        this.codeValueSeq = codeValueSeq;
    }
}
