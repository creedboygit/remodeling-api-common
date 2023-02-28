package com.hanssem.remodeling.common.api.service.search;

import com.hanssem.remodeling.common.api.controller.search.request.AddressRequest;
import com.hanssem.remodeling.common.api.controller.search.request.BuildRequest;
import com.hanssem.remodeling.common.api.controller.search.request.PlanRequest;
import com.hanssem.remodeling.common.api.controller.search.response.AddressResponse;
import com.hanssem.remodeling.common.api.controller.search.response.PlanResponse;
import org.springframework.data.domain.Page;

public interface KonanSearch {

    Page<AddressResponse> searchAddress(AddressRequest searchRequest) throws Exception;

    Page<AddressResponse> searchAddressByBldgMgmtNo(BuildRequest request) throws Exception;

    Page<PlanResponse> searchBlueprint(PlanRequest searchRequest) throws Exception;

}