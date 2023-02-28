package com.hanssem.remodeling.common.admin.service.commoncode;

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
public class AddCodeValueDto {

    private String codeName;
    private String codeValue;
    private String codeValueName;
    private String description;
    private int codeValueSeq;
    private LocalDateTime validEndDatetime;
    private String upperCodeValueId;
    private String etcValue1;
    private String etcValue2;

}
