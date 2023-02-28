package com.hanssem.remodeling.common.api.controller.common;

import static com.hanssem.remodeling.common.api.service.scrap.mapper.ScrapMapper.SCRAP_MAPPER;

import com.hanssem.remodeling.common.api.controller.common.request.AddScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.request.DeleteScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.request.ExistScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.request.ScrapRequest;

import com.hanssem.remodeling.common.api.service.scrap.ScrapService;
import com.hanssem.remodeling.common.common.annotation.OnlyLogin;

import com.hanssem.remodeling.common.api.controller.common.response.ScrapCategoryResponse;
import com.hanssem.remodeling.common.api.controller.common.response.ScrapContentResponse;
import com.hanssem.remodeling.common.constant.ScrapTypeCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Tag(name = "스크랩")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/commons/scraps")
public class ScrapController {
    private final ScrapService scrapService;

    @Operation(summary = "스크랩 등록", description = "스크랩 등록", responses = {@ApiResponse(content = @Content(schema = @Schema()))})
    @OnlyLogin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addScraps(@RequestBody @Valid final AddScrapRequest request) {
        scrapService.addScraps(SCRAP_MAPPER.addScrapsRequestToDto(request));
    }

    @Operation(summary = "스크랩 삭제", description = "스크랩 삭제", responses = {@ApiResponse(content = @Content(schema = @Schema()))})
    @OnlyLogin
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteScraps(@RequestBody @Valid final DeleteScrapRequest request) {
        scrapService.deleteScraps(SCRAP_MAPPER.deleteScrapsRequestToDto(request));
    }

    @Operation(summary = "스크랩 여부", description = "스크랩 여부", responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Boolean.class)))})
    @OnlyLogin
    @GetMapping(value = "/existence")
    public boolean existScraps(@Valid final ExistScrapRequest request) {
        return scrapService.existScraps(SCRAP_MAPPER.existScrapsRequestToDto(request));

    }

    @Operation(summary = "스크랩 콘텐츠 조회 By Header Jwt Token", description = "인증토큰을 이용한 스크랩 콘텐츠 조회",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = ScrapContentResponse.class)))})
    @OnlyLogin
    @GetMapping(value = "")
    public ScrapContentResponse getScrapContent(ScrapRequest request) {
        return scrapService.getScrapContent(request);
    }

    @Operation(summary = "스크랩 카테고리 조회", responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = ScrapCategoryResponse.class)))})
    @OnlyLogin
    @GetMapping(value = "/categories")
    public ScrapCategoryResponse getScrapCategories(@RequestParam(name = "scrapTypeCode") List<ScrapTypeCode> scrapTypeCode) {
        return scrapService.getScrapCategories(scrapTypeCode);
    }
}
