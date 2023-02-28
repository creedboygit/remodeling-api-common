package com.hanssem.remodeling.common.api.controller.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.NotBlank;

@Schema(name = "ConstructCaseRequest", description = "시공사례 조회 요청 객체 (한샘몰 API)")
@Getter
@Setter
@ParameterObject
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartCountRequest {

    @NotBlank(message = "custNo을 입력해주세요.")
    @Schema(name = "custNo", title = "custNo", example = "123345")
    private String custNo;
}
