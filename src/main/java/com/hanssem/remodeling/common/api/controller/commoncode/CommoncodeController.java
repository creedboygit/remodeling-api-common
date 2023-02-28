package com.hanssem.remodeling.common.api.controller.commoncode;

import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeGroupResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeValueResponse;
import com.hanssem.remodeling.common.api.service.commoncode.CommoncodeService;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.constant.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "공통코드")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/commoncode", produces = MediaType.APPLICATION_JSON_VALUE)

public class CommoncodeController {

    private final CommoncodeService commoncodeService;

    @Operation(
        summary = "공통코드 목록 조회", description = "",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = CommoncodeResponse.class)))})
    @GetMapping(value = "")
    public List<CommoncodeResponse> findCommoncodeList() {
        return commoncodeService.findCommoncodeList();
    }

    @Operation(
        summary = "공통코드 상세 조회", description = "",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = CommoncodeValueResponse.class)))})
    @GetMapping(value = "/{codeName}")
    public List<CommoncodeValueResponse> findCommoncodeValues(@PathVariable("codeName") final String codeName) {
        if (codeName == null || codeName.isEmpty()) {
            throw new CustomException(ResponseCode.INVALID_PARAMS);
        }
        return commoncodeService.findCommoncodeValues(codeName);
    }

    @Operation(
        summary = "공통코드 그룹 상세 조회", description = "",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = CommoncodeResponse.class)))})
    @GetMapping(value = "/group/{groupName}")
    public List<CommoncodeGroupResponse> findGroupList(@PathVariable("groupName") final String groupName) {
        return commoncodeService.findGroupList(groupName);
    }

    @Operation(summary = "Cache clear - 전체", description = "캐시 전체 삭제.")
    @DeleteMapping(value = "/cache-clear-all")
    public void clearCacheAll() {
        commoncodeService.clearCacheAll();
    }
}
