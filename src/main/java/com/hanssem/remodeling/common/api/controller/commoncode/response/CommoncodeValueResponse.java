package com.hanssem.remodeling.common.api.controller.commoncode.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommoncodeValueResponse {

    @Schema(title = "codeId", description = "공통코드 그룹 ID", example = "8F9787D24F4A11ED9DDCE68E18CDBD49")
    private String codeId;

    @Schema(title = "codeValueId", description = "공통코드 코드 value ID", example = "'8F9787D24F4A11ED9DDCE68E18CDBD40'")
    private String codeValueId;

    @Schema(title = "codeValue", description = "공통코드 value 코드명", example = "N")
    private String codeValue;

    @Schema(title = "codeValueName", description = "공통코드 코드 value 한글명", example = "")
    private String codeValueName;

    @Schema(title = "codeValueSeq", description = "공통코드 코드 value 노출 순서", example = "")
    private int codeValueSeq;

    @Schema(title = "description", description = "공통코드 코드 설명", example = "")
    private String description;

    @Schema(title = "validEndDatetime", description = "공통코드 코드 유효일자", example = "")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime validEndDatetime;

    @Schema(title = "upperCodeValueId", description = "공통코드 코드 상위코드 ID", example = "")
    private String upperCodeValueId;

    @Schema(title = "etcValue1", description = "공통코드 코드 기타1", example = "")
    private String etcValue1;

    @Schema(title = "etcValue1", description = "공통코드 코드 기타2", example = "")
    private String etcValue2;
}
