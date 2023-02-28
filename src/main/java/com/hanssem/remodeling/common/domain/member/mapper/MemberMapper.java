package com.hanssem.remodeling.common.domain.member.mapper;

import com.hanssem.remodeling.common.api.controller.member.request.PathTestRequest;
import com.hanssem.remodeling.common.api.controller.member.request.UpdateMemberRequest;
import com.hanssem.remodeling.common.api.controller.member.response.MembersResponse.MemberResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse2;
import com.hanssem.remodeling.common.api.service.member.dto.MemberInfoDto;
import com.hanssem.remodeling.common.common.model.CdnPath;
import com.hanssem.remodeling.common.domain.member.entity.Member;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    List<MemberInfoDto> toDto(List<Member> members);
    List<MemberResponse> toResponse(List<MemberInfoDto> members);

    @Mapping(source = "name", target = "newName")
    MemberInfoDto toDto(Member member);

    @Mapping(source = "newName", target = "name")
    MemberResponse toResponse(MemberInfoDto member);

    /**

     특정 필드를 매핑에서 제외하고 싶을 경우
     @Mapping(target = "memberId", ignore = true)
     특정 필드의 값을 검증하여 적용하고 싶은 경우
     @Mapping(target = "handselPay", expression = "java( dto.getHandselPay() > 0 ? dto.getHandselPay() : entity.getHandselPay() )")
     BeanMapping 하단에 추가
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "memberId", ignore = true)
    void partialMemberUpdate(@MappingTarget Member member, UpdateMemberRequest request);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="pathData", expression = "java( stringToCdnPath(request.getPathData()) )")
	PathTestResponse pathTestRequestToResponse(PathTestRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="pathData", expression = "java(com.hanssem.remodeling.common.common.helper.GlobalConstants.makeCdnUrl(request.getPathData()) )")
    PathTestResponse2 pathTestRequestToResponse2(PathTestRequest request);


    default CdnPath stringToCdnPath(String path) {
		  return CdnPath.builder().value(path).build();
	  }
}
