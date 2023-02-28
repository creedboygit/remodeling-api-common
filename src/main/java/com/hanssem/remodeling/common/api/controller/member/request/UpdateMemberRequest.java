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
public final class UpdateMemberRequest {

    /**
     * 변경이 가능한 모든 필드는 Request Param에서 비포함 될수 있기 때문에 required = false로 지정해야 한다.
     */
    @NotBlank(message = "값을 입력해주세요.")
    @Schema(name = "name", title = "이름", type = "String", required = false, example = "iceman")
    private String name;

    @Schema(name = "type", title = "종류", type = "co.edithome.appname.domain.enums.MemberType", allowableValues = "(ADMIN, MEMBER, BUSINESS_MEMBER)", required = true, example = "MEMBER")
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
