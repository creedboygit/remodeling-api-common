package com.hanssem.remodeling.common.api.controller.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallScrapVo;
import com.hanssem.remodeling.common.common.response.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ScrapContentResponse {
    @JsonIgnore
    @Builder.Default
    private List<ScrapContentResponse.ScrapDto> scrapList = new ArrayList<>();
    private PageResponse<ScrapContentResponse.ScrapDto> scraps;

    public void addScrap(HanssemMallScrapVo.Scrap scrapData) {
        scrapList.add(ScrapContentResponse.ScrapDto.of(scrapData));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Schema(title = "스크랩 데이터")
    public static class ScrapDto {
        @Schema(title = "ID")
        private int id;
        @Schema(title = "스크랩 제목")
        private String title;
        @Schema(title = "콘텐츠 랜딩 URL")
        private String landingUrl;
        @Schema(title = "이미지 URL")
        private String imageUrl;

        public static ScrapContentResponse.ScrapDto of(HanssemMallScrapVo.Scrap scrapData) {
            return ScrapContentResponse.ScrapDto.builder()
                    .id(scrapData.getId())
                    .title(scrapData.getTitle())
                    .landingUrl(scrapData.getContentsUrl())
                    .imageUrl(scrapData.getImageUrl())
                    .build();
        }
    }
}
