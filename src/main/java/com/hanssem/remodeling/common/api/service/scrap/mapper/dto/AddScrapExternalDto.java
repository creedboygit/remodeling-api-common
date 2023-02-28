package com.hanssem.remodeling.common.api.service.scrap.mapper.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AddScrapExternalDto {

    private String wishGbnCd;
    private long targetNo;
    private String imgUrl;

    private String title;
    private String detailUrl;
}
