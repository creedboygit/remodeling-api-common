package com.hanssem.remodeling.common.api.service.scrap;

import static com.hanssem.remodeling.common.api.service.scrap.mapper.ScrapMapper.SCRAP_MAPPER;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanssem.remodeling.common.api.controller.common.request.ScrapRequest;
import com.hanssem.remodeling.common.api.controller.common.response.ScrapCategoryResponse;
import com.hanssem.remodeling.common.api.controller.common.response.ScrapContentResponse;
import com.hanssem.remodeling.common.api.repository.client.HanssemMallClient;
import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallAPIBaseVo;
import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallScrapVo;
import com.hanssem.remodeling.common.api.service.scrap.dto.AddScrapDto;
import com.hanssem.remodeling.common.api.service.scrap.dto.DefaultScrapDto;
import com.hanssem.remodeling.common.api.service.scrap.mapper.vo.ExistScrapVo;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.response.PageResponse;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.hanssem.remodeling.common.constant.ScrapTypeCode;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScrapService {

    private final HanssemMallClient hanssemMallClient;
    private final ObjectMapper objectMapper;

    public ScrapService(HanssemMallClient hanssemMallClient, ObjectMapper objectMapper) {
        this.hanssemMallClient = hanssemMallClient;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = objectMapper;
    }

    public void addScraps(final AddScrapDto dto) {
        final var result = hanssemMallClient.addScraps(SCRAP_MAPPER.addScrapsDtoToExternalDto(dto));
        try {
            final var resultVo = objectMapper.readValue(result, HanssemMallAPIBaseVo.class);
            resultVo.validateResponse();
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }

    public void deleteScraps(final DefaultScrapDto dto) {
        final var result = hanssemMallClient.deleteScraps(SCRAP_MAPPER.defaultScrapsDtoToExternalDto(dto));
        try {
            final var resultVo = objectMapper.readValue(result, HanssemMallAPIBaseVo.class);
            resultVo.validateResponse();
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }

    public boolean existScraps(final DefaultScrapDto dto) {
        final var result = hanssemMallClient.existScraps(SCRAP_MAPPER.defaultScrapsDtoToExternalDto(dto));
        try {
            final var resultVo = objectMapper.readValue(result, ExistScrapVo.class);
            resultVo.validateResponse();
            return "Y".equals(resultVo.getWishYn());
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }

    public ScrapContentResponse getScrapContent(ScrapRequest request) {
        ScrapContentResponse scrapResponse = new ScrapContentResponse();
        final var result = hanssemMallClient.getScraps(request.getPageable().getPageNumber() + 1, request.getPageable().getPageSize(), request.getScrapTypeCode().getOriginalCode());
        try {
            final var resultVo = objectMapper.readValue(result, HanssemMallScrapVo.class);
            resultVo.validateResponse();
            for (HanssemMallScrapVo.Scrap contents : resultVo.getData()) {
                if (contents.getType().equals(ScrapTypeCode.IMAGE.getOriginalCode())) {
                    contents.setId(contents.getSubId());
                }
                scrapResponse.addScrap(contents);
            }
            return ScrapContentResponse.builder()
                    .scraps(PageResponse.of(
                                    scrapResponse.getScrapList(),
                                    request.getPageable(),
                                    resultVo.getPaging().getTotalCount()
                            )
                    )
                    .build();
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }

    public ScrapCategoryResponse getScrapCategories(List<ScrapTypeCode> scrapTypeCode) {
        ScrapCategoryResponse scrapCategoryResponse = new ScrapCategoryResponse();
        for (ScrapTypeCode typeCode : scrapTypeCode) {
            final var result = hanssemMallClient.getScraps(1, 500, typeCode.getOriginalCode());
            try {
                final var resultVo = objectMapper.readValue(result, HanssemMallScrapVo.class);
                resultVo.validateResponse();
                scrapCategoryResponse.addCategory(
                        typeCode, typeCode.getType(), resultVo.getPaging().getTotalCount());
            } catch (JsonProcessingException e) {
                throw new CustomException(ResponseCode.ERROR);
            }
        }
        return scrapCategoryResponse.builder()
                .categories(scrapCategoryResponse.getCategories())
                .build();
    }
}