package com.hanssem.remodeling.common.api.controller.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "MembersResponse", description = "회원 정보 목록 데이터")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class MembersResponse implements Serializable {

    @Schema(name = "members", title = "회원 정보 목록", type = "com.hanssem.remodeling.common.api.controller.member.response.MembersResponse.MemberResponse", required = true, example = "1")
    private List<MemberResponse> members;

    public static MembersResponse newInstance(final List<MemberResponse> members) {
        return new MembersResponse(members);
    }

    @Schema(title = "MemberResponse", description = "회원 정보 데이터")
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Getter
    public static class MemberResponse implements Serializable {
        @Schema(name = "memberId", title = "회원 아이디", type = "Long", required = true, example = "1")
        private Long memberId;

        @Schema(name = "name", title = "회원 이름", type = "String", required = true, example = "iceman")
        private String name;

        @Schema(name = "type", title = "회원 종류", type = "String", required = true, example = "ADMIN")
        private String type;

        public static MemberResponse of(final Long memberId, final String name, final String type) {
            return new MemberResponse(memberId, name, type);
        }
    }
}
