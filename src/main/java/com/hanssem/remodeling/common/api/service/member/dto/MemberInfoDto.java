package com.hanssem.remodeling.common.api.service.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberInfoDto {
    private Long memberId;
    private String newName;
    private String type;
}
