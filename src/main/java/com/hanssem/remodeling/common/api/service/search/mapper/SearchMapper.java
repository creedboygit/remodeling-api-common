package com.hanssem.remodeling.common.api.service.search.mapper;

import com.hanssem.remodeling.common.api.controller.search.response.AddressResponse;
import com.hanssem.remodeling.common.api.controller.search.response.PlanResponse;
import com.hanssem.remodeling.common.api.service.search.dto.HanssemMallAddressVo.Address;
import com.hanssem.remodeling.common.api.service.search.dto.HanssemMallBlueprintVo.Blueprint;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SearchMapper {
    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);


    AddressResponse toResponse(Address address);
    List<AddressResponse> toResponseList(List<Address> responseVoList);


    @Mapping(
        target = "bay",
        expression = "java(Integer.parseInt(responseVo.getBay().isEmpty() ? \"0\" : responseVo.getBay()))")
    PlanResponse toBlueprintResponse(Blueprint responseVo);

    List<PlanResponse> toBlueprintResponseList(List<Blueprint> responseVoList);
}
