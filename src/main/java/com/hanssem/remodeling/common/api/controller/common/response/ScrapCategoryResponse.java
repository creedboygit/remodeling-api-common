package com.hanssem.remodeling.common.api.controller.common.response;

import com.hanssem.remodeling.common.constant.ScrapTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class ScrapCategoryResponse {
    @Builder.Default
    private List<ScrapCategoryResponse.CategoryDto> categories = new ArrayList<>();

    public void addCategory(ScrapTypeCode type, String name, int cnt) {
        categories.add(ScrapCategoryResponse.CategoryDto.of(type, name, cnt));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Schema(title = "카테고리 데이터")
    public static class CategoryDto {
        @Schema(title = "타입")
        private ScrapTypeCode type;
        @Schema(title = "이름")
        private String name;
        @Schema(title = "카운트")
        private int cnt;

        public static ScrapCategoryResponse.CategoryDto of(final ScrapTypeCode type, final String name, final int cnt) {
            return ScrapCategoryResponse.CategoryDto.builder()
                    .type(type)
                    .name(name)
                    .cnt(cnt)
                    .build();
        }
    }
}
