package com.hanssem.remodeling.common.api.repository.member;

import com.hanssem.remodeling.common.api.controller.member.request.SearchRequest;
import com.hanssem.remodeling.common.api.service.member.dto.MemberInfoDto;
import com.hanssem.remodeling.common.common.response.PageResponse;
import java.util.List;

public interface MemberQueryRepository {
    List<MemberInfoDto> getMembers();
    PageResponse<MemberInfoDto> searchMembers(SearchRequest request);
}
