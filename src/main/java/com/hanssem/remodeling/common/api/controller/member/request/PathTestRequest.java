package com.hanssem.remodeling.common.api.controller.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(name = "PathTestRequest", description = "회원 생성용 데이터")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public final class PathTestRequest {

    @NotBlank(message = "값을 입력해주세요.")
    @Schema(name = "pathData", title = "도메인 붙기전 데이터", type = "String", required = true, example = "/img-test/my_profile.png")
    private String pathData;

}
