package com.hanssem.remodeling.common.api.service.commoncode.mapper;

import com.hanssem.remodeling.common.admin.service.commoncode.dto.AddCodeDto;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeGroupResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeValueResponse;
import com.hanssem.remodeling.common.domain.commoncode.entity.Commoncode;
import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommoncodeMapper {
    CommoncodeMapper INSTANCE = Mappers.getMapper(CommoncodeMapper.class);

    @Mapping(source = "commoncode.codeId", target = "codeId")
    List<CommoncodeGroupResponse> toGroupResponseList(List<Commoncode> commoncodeList);
    List<CommoncodeResponse> toCommoncodeResponseList(List<Commoncode> commoncodeList);

    @Mapping(source = "commoncode.codeId", target = "codeId")
    CommoncodeValueResponse toResponse(CommoncodeValue commoncodeValue);
    List<CommoncodeValueResponse> toResponseList(List<CommoncodeValue> commoncodeValues);

    @Mapping(source = "codeValueId", target = "codeId")
    @Mapping(source = "commoncode.codeId", target = "upperCodeId")
    @Mapping(source = "codeValueName", target = "codeKorName")
    @Mapping(source = "codeValue", target = "codeName")
    CommoncodeResponse toCommoncodeResponse(CommoncodeValue commoncodeValue);


    Commoncode toCodeEntity(AddCodeDto codeDto);
}
