package com.hanssem.remodeling.common.api.controller.member.request;

import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.model.MemberType;
import com.hanssem.remodeling.common.constant.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(name = "AddMemberRequest", description = "회원 생성용 데이터")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public final class AddMemberRequest {

    @NotBlank(message = "값을 입력해주세요.")
    @Schema(name = "name", title = "이름", type = "String", required = true, example = "iceman")
    private String name;

    @Schema(name = "type", title = "종류", type = "com.hanssem.remodeling.common.common.model.MemberType", allowableValues = "(ADMIN, MEMBER, BUSINESS_MEMBER)", required = true, example = "MEMBER")
    private MemberType type;

    public void vaild() {
        /*
        * 디테일한 예
        * */
        if (type == MemberType.ADMIN && !"관리자".contains(name)) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }

}
