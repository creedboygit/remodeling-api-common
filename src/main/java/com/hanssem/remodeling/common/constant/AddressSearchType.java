package com.hanssem.remodeling.common.constant;

import lombok.Getter;

@Getter
public enum AddressSearchType {
    ALL("전체", "ALL"),
    KONAN("코난", "KONAN"),
    GOVERNMENT("행안부", "GOVERMENT");

    private String type;
    private String originalCode;

    AddressSearchType(String type, String originalCode) {
        this.type = type;
        this.originalCode = originalCode;
    }
}
