package com.hanssem.remodeling.common.api.controller.search.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "AddressResponse", description = "주소검색 응답객체")
public class AddressResponse {

    @Schema(title = "houseCd", description = "한샘 주택 DB 아파트코드", example = "")
    private String houseCd;
    @Schema(title = "bldgMgmtNo", description = "대표건물관리번호", example = "")
    private String bldgMgmtNo;
    @Schema(title = "bldgNm", description = "건물명", example = "")
    private String bldgNm;
    @Schema(title = "aptYn", description = "아파트여부", example = "")
    private String aptYn;
    @Schema(title = "lglSidoCd", description = "법정시도코드", example = "")
    private String lglSidoCd;
    @Schema(title = "lglSiguCd", description = "법정시군구코드", example = "")
    private String lglSiguCd;
    @Schema(title = "lglDngCd", description = "법정동코드", example = "")
    private String lglDngCd;
    @Schema(title = "bunji", description = "번지", example = "")
    private int bunji;
    @Schema(title = "ho", description = "호", example = "")
    private int ho;
    @Schema(title = "roadCd", description = "도로명코드", example = "")
    private String roadCd;
    @Schema(title = "bldgMainNo", description = "건물본번", example = "")
    private int bldgMainNo;
    @Schema(title = "bldgSubNo", description = "건물부번", example = "")
    private int bldgSubNo;
    @Schema(title = "post5No", description = "우편번호", example = "")
    private String post5No;
    @Schema(title = "sidoNm", description = "주소_시", example = "")
    private String sidoNm;
    @Schema(title = "siguNm", description = "주소_구", example = "")
    private String siguNm;
    @Schema(title = "dngNm", description = "주소_동", example = "")
    private String dngNm;
    @Schema(title = "riNm", description = "주소_리", example = "")
    private String riNm;
    @Schema(title = "addrJibun", description = "지번주소", example = "")
    private String addrJibun;
    @Schema(title = "addrRoad", description = "도로명주소", example = "")
    private String addrRoad;
    @Schema(title = "grs80XBldgAvg", description = "단지X좌표(GRS80 UTM-K좌표평균값)", example = "")
    private String grs80XBldgAvg;
    @Schema(title = "grs80YBldgAvg", description = "단지Y좌표(GRS80 UTM-K좌표평균값)", example = "")
    private String grs80YBldgAvg;
    @Schema(title = "wgs84XBldgAvg", description = "단지X좌표(WGS84)", example = "")
    private String wgs84XBldgAvg;
    @Schema(title = "wgs84YBldgAvg", description = "단지Y좌표(WGS84)", example = "")
    private String wgs84YBldgAvg;

}


