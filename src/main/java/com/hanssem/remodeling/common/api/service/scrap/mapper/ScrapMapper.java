package com.hanssem.remodeling.common.api.service.scrap.mapper;

import com.hanssem.remodeling.common.api.controller.common.request.AddScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.request.DeleteScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.request.ExistScrapRequest;
import com.hanssem.remodeling.common.api.service.scrap.dto.AddScrapDto;
import com.hanssem.remodeling.common.api.service.scrap.dto.DefaultScrapDto;
import com.hanssem.remodeling.common.api.service.scrap.mapper.dto.AddScrapExternalDto;
import com.hanssem.remodeling.common.api.service.scrap.mapper.dto.DefaultScrapExternalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScrapMapper {
    ScrapMapper SCRAP_MAPPER = Mappers.getMapper(ScrapMapper.class);


    @Mapping(target = "targetId", source = "target.id")
    @Mapping(target = "targetLandingUrl", source = "target.landingUrl")
    @Mapping(target = "targetName", source = "target.name")
    @Mapping(target = "targetImageUrl", source = "target.imageUrl")
    AddScrapDto addScrapsRequestToDto(final AddScrapRequest request);

    @Mapping(target = "wishGbnCd", expression = "java(dto.getScrapTypeCode().getOriginalCode())")
    @Mapping(target = "targetNo", source = "targetId")
    @Mapping(target = "imgUrl", source = "targetImageUrl")
    @Mapping(target = "title", source = "targetName")
    @Mapping(target = "detailUrl", source = "targetLandingUrl")
    AddScrapExternalDto addScrapsDtoToExternalDto(final AddScrapDto dto);

    DefaultScrapDto deleteScrapsRequestToDto(final DeleteScrapRequest request);

    @Mapping(target = "wishGbnCd", expression = "java(dto.getScrapTypeCode().getOriginalCode())")
    @Mapping(target = "targetNo", expression = "java(String.valueOf(dto.getTargetId()))")
    DefaultScrapExternalDto defaultScrapsDtoToExternalDto(final DefaultScrapDto dto);

    DefaultScrapDto existScrapsRequestToDto(final ExistScrapRequest request);
}
