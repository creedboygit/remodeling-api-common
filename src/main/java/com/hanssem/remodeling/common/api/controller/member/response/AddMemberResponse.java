package com.hanssem.remodeling.common.api.controller.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "AddMemberResponse", description = "회원 등록 후 데이터")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddMemberResponse implements Serializable {
    @Schema(name = "memberId", title = "회원 아이디", type = "Long", required = true, example = "1")
    private Long memberId;

    public static AddMemberResponse of(final Long memberId) {
        return new AddMemberResponse(memberId);
    }
}
