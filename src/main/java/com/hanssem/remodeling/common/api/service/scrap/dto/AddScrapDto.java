package com.hanssem.remodeling.common.api.service.scrap.dto;

import com.hanssem.remodeling.common.constant.ScrapTypeCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AddScrapDto {

    private ScrapTypeCode scrapTypeCode;
    private long targetId;
    private String targetName;
    private String targetLandingUrl;
    private String targetImageUrl;
}
