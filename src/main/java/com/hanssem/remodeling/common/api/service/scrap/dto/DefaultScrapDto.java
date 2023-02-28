package com.hanssem.remodeling.common.api.service.scrap.dto;

import com.hanssem.remodeling.common.constant.ScrapTypeCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DefaultScrapDto {

    private ScrapTypeCode scrapTypeCode;
    private long targetId;
}
