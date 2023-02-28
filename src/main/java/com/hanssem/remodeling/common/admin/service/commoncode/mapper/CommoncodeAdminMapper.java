package com.hanssem.remodeling.common.admin.service.commoncode.mapper;

import com.hanssem.remodeling.common.admin.service.commoncode.AddCodeValueDto;
import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommoncodeAdminMapper {
    CommoncodeAdminMapper INSTANCE = Mappers.getMapper(CommoncodeAdminMapper.class);


    List<CommoncodeValue> toCommoncodeValueEntity(List<AddCodeValueDto> collect);
}
