package com.hanssem.remodeling.common.api.service.scrap.mapper.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DefaultScrapExternalDto {

    private String wishGbnCd;
    private String targetNo;
}
