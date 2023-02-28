package com.hanssem.remodeling.common.api.service.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanssem.remodeling.common.api.controller.search.request.AddressRequest;
import com.hanssem.remodeling.common.api.controller.search.request.BuildRequest;
import com.hanssem.remodeling.common.api.controller.search.request.PlanRequest;
import com.hanssem.remodeling.common.api.controller.search.response.AddressResponse;
import com.hanssem.remodeling.common.api.controller.search.response.PlanResponse;
import com.hanssem.remodeling.common.api.repository.client.HanssemMallClient;
import com.hanssem.remodeling.common.api.service.search.dto.HanssemMallAddressVo;
import com.hanssem.remodeling.common.api.service.search.dto.HanssemMallBlueprintVo;
import com.hanssem.remodeling.common.api.service.search.mapper.SearchMapper;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.response.PageResponse;
import com.hanssem.remodeling.common.constant.ResponseCode;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KonanSearchClientImpl implements KonanSearch {

    private final HanssemMallClient hanssemMallClient;
    private final ObjectMapper objectMapper;

    public KonanSearchClientImpl(HanssemMallClient hanssemMallClient, ObjectMapper objectMapper) {
        this.hanssemMallClient = hanssemMallClient;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<AddressResponse> searchAddress(AddressRequest request) throws Exception {

        List<HanssemMallAddressVo.Address> responseVoList = new ArrayList<>();
        int totalCount = 0;

        final var result = hanssemMallClient.getAddress(request.getPageable().getPageNumber(), request.getPageable().getPageSize(), request.getSearchType().getOriginalCode(), request.getQuery());
        try {
            final var resultVo = objectMapper.readValue(result, HanssemMallAddressVo.class);
            if (resultVo.getData() == null) {
                throw new CustomException(ResponseCode.DATA_NOT_FOUND);
            }
            totalCount = resultVo.getPaging().getTotalRecordCount();
            for (HanssemMallAddressVo.Address contents : resultVo.getData()) {
                responseVoList.add(contents);
            }
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }

        List<AddressResponse> addressResponseList = SearchMapper.INSTANCE.toResponseList(responseVoList);
        return PageResponse.of(addressResponseList, request.getPageable(), totalCount);
    }

    /**
     * 한샘 모바일 API에서 제공하지 않는 기능
     */
    @Override
    public Page<AddressResponse> searchAddressByBldgMgmtNo(BuildRequest request) throws Exception {
        return null;
    }

    @Override
    public Page<PlanResponse> searchBlueprint(PlanRequest request) throws Exception {
        List<HanssemMallBlueprintVo.Blueprint> responseVoList = new ArrayList<>();
        int totalCount = 0;

        final var result = hanssemMallClient.getBlueprint(request.getPageable().getPageNumber(), request.getPageable().getPageSize(), request.getHouseCd(), request.getBldgMgmtNo());
        try {
            final var resultVo = objectMapper.readValue(result, HanssemMallBlueprintVo.class);
            resultVo.validateResponse();
            if (resultVo.getData() == null) {
                throw new CustomException(ResponseCode.DATA_NOT_FOUND);
            }
            totalCount = resultVo.getPaging().getTotalRecordCount();
            for (HanssemMallBlueprintVo.Blueprint contents : resultVo.getData()) {
                responseVoList.add(contents);
            }
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }

        List<PlanResponse> planResponseList = SearchMapper.INSTANCE.toBlueprintResponseList(responseVoList);
        return PageResponse.of(planResponseList, request.getPageable(), totalCount);
    }
}
