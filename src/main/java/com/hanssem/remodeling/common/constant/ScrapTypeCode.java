package com.hanssem.remodeling.common.constant;


import lombok.Getter;
@Getter
public enum ScrapTypeCode {
    ALL("전체", null),
    STORE_GOODS("스토어상품", "GS"),
    REMODELING_GOODS("리모델링상품", "GR"),
    CONSTRUCTION_CASE("시공사례", "HC"),
    IMAGE("사진", "HI"),
    MAGAZINE("매거진", "HE"),
    SHOP("매장", "SH"),
            ;
    private String type;
    private String originalCode;

    ScrapTypeCode(String type, String originalCode) {
        this.type = type;
        this.originalCode = originalCode;
    }
}
