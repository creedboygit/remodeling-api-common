package com.hanssem.remodeling.common.api.service.scrap.mapper.vo;

import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallAPIBaseVo;
import lombok.Getter;

@Getter
public class ExistScrapVo extends HanssemMallAPIBaseVo {

    private String wishGbnCd;
    private String targetNo;
    private String targetSubNo;
    private String wishYn;

    public ExistScrapVo() {
        super();
    }
}
