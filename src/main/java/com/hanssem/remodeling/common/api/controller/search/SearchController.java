package com.hanssem.remodeling.common.api.controller.search;

import com.hanssem.remodeling.common.api.controller.search.request.AddressRequest;
import com.hanssem.remodeling.common.api.controller.search.request.BuildRequest;
import com.hanssem.remodeling.common.api.controller.search.request.PlanRequest;
import com.hanssem.remodeling.common.api.controller.search.response.AddressResponse;
import com.hanssem.remodeling.common.api.controller.search.response.PlanResponse;
import com.hanssem.remodeling.common.api.service.search.KonanSearchClientImpl;
import com.hanssem.remodeling.common.api.service.search.KonanSearchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "주소검색")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    private final KonanSearchClientImpl searchClient;
    private final KonanSearchServiceImpl searchService;

    @Operation(
        summary = "주소검색",
        description = "주소검색 - 키워드, 건물관리번호로 검색",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = AddressResponse.class)))})
    @GetMapping(value = "/address")
    public Page<AddressResponse> searchAddress(@Valid AddressRequest searchRequest) throws Exception {
        searchRequest.verify();
        return searchClient.searchAddress(searchRequest);
    }

    @Operation(
        summary = "건물관리번호로 주소검색하기",
        description = "주소검색 - 건물관리번호로 검색",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = AddressResponse.class)))})
    @GetMapping(value = "/buildno")
    public Page<AddressResponse> searchAddressByBldgMgmtNo(BuildRequest request) throws Exception {
        request.verify();
        return searchService.searchAddressByBldgMgmtNo(request);
    }

    @Operation(
        summary = "도면검색 (홈퍼니싱api)",
        description = " '하우스 코드', '건물관리번호'중 1개 입력 필수."
                    + "'건물관리번호'만 입력되는 경우 '건물관리번호'로 '하우스 코드'조회하여 '하우스 코드'로 도면조회",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = PlanResponse.class)))})
    @GetMapping(value = "/blueprints")
    public Page<PlanResponse> searchBlueprint(PlanRequest request) throws Exception {
        request.verify();
        return searchClient.searchBlueprint(request);
    }

    @Operation(
        summary = "도면검색 (리모델링api)",
        description = " '하우스 코드', '건물관리번호'중 1개 입력 필수."
            + "'건물관리번호'만 입력되는 경우 '건물관리번호'로 '하우스 코드'조회하여 '하우스 코드'로 도면조회",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = PlanResponse.class)))})
    @GetMapping(value = "/blueprints2")
    public Page<PlanResponse> searchBlueprint2(PlanRequest request) throws Exception {
        request.verify();
        return searchService.searchBlueprint(request);
    }
}
