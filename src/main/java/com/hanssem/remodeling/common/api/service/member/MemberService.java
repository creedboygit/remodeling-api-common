package com.hanssem.remodeling.common.api.service.member;

import com.hanssem.remodeling.common.api.controller.member.request.AddMemberRequest;
import com.hanssem.remodeling.common.api.controller.member.request.PathTestRequest;
import com.hanssem.remodeling.common.api.controller.member.request.SearchRequest;
import com.hanssem.remodeling.common.api.controller.member.request.UpdateMemberRequest;
import com.hanssem.remodeling.common.api.controller.member.response.AddMemberResponse;
import com.hanssem.remodeling.common.api.controller.member.response.MembersResponse;
import com.hanssem.remodeling.common.api.controller.member.response.MembersResponse.MemberResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse2;
import com.hanssem.remodeling.common.api.repository.member.MemberQueryRepository;
import com.hanssem.remodeling.common.api.repository.member.MemberRepository;
import com.hanssem.remodeling.common.api.service.member.dto.MemberInfoDto;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.response.PageResponse;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.hanssem.remodeling.common.domain.member.entity.Member;
import com.hanssem.remodeling.common.domain.member.mapper.MemberMapper;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public AddMemberResponse addMember(final AddMemberRequest request) {
        if (!Objects.isNull(memberRepository.findByName(request.getName()))) {
            throw new CustomException(ResponseCode.ERROR);
        }

        return AddMemberResponse
            .of(memberRepository.save(Member.of(request.getName(), request.getType())).getMemberId());
    }

    public MembersResponse getMembers() {
        return result(
            memberRepository.findAll().stream().map(member ->
                MemberResponse.of(member.getMemberId(), member.getName(), member.getType().name())
            ).collect(Collectors.toList()));
    }

    public MembersResponse getMembersByMapstruct() {
        return result(MemberMapper.INSTANCE.toResponse(memberQueryRepository.getMembers()));
    }

    public PageResponse<MemberInfoDto> getMembersByPaging(SearchRequest request) {
        return (PageResponse<MemberInfoDto>) memberQueryRepository.searchMembers(request);
    }

    private MembersResponse result(final List<MemberResponse> data) {
        return MembersResponse.newInstance(data);
    }

    public MemberResponse updateMember(long memberId, UpdateMemberRequest request) {
        Member tempData = memberRepository.findById(memberId).get();
        MemberMapper.INSTANCE.partialMemberUpdate(tempData, request);
        // 변경 내역을 저장하고 싶으면 memberRepository.save(tempData);해야함
        return MemberResponse.of(tempData.getMemberId(), tempData.getName(), tempData.getType().name());
    }

	public PathTestResponse pathTest(@Valid PathTestRequest request) {
		PathTestResponse result = MemberMapper.INSTANCE.pathTestRequestToResponse(request);
		return result;
	}

    public PathTestResponse2 pathTest2(@Valid PathTestRequest request) {
        PathTestResponse2 result = MemberMapper.INSTANCE.pathTestRequestToResponse2(request);
        return result;
    }
}
