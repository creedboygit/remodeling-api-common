package com.hanssem.remodeling.common.api.service.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallAPIBaseVo;
import java.util.List;
import lombok.Getter;

@Getter
public class HanssemMallAddressVo extends HanssemMallAPIBaseVo {

    private Paging paging;
    private List<Address> data;

    private HanssemMallAddressVo(
        @JsonProperty(value = "code") int code,
        @JsonProperty(value = "message") String message,
        @JsonProperty(value = "pagingMap") Paging paging,
        @JsonProperty(value = "houseList") List<Address> data) {
        super(code, message);
        this.paging = paging;
        this.data = data;
    }

    public List<Address> getData() {
        super.validateResponse();
        return data;
    }

    @Getter
    public static class Address {

        @JsonProperty("HOUSE_CD")
        private String houseCd;
        @JsonProperty("BLDG_MGMT_NO")
        private String bldgMgmtNo;
        @JsonProperty("BLDG_NM")
        private String bldgNm;
        @JsonProperty("APT_YN")
        private String aptYn;
        @JsonProperty("LGL_SIDO_CD")
        private String lglSidoCd;
        @JsonProperty("LGL_SIGU_CD")
        private String lglSiguCd;
        @JsonProperty("LGL_DNG_CD")
        private String lglDngCd;
        @JsonProperty("BUNJI")
        private int bunji;
        @JsonProperty("HO")
        private int ho;
        @JsonProperty("ROAD_CD")
        private String roadCd;
        @JsonProperty("BLDG_MAIN_NO")
        private int bldgMainNo;
        @JsonProperty("BLDG_SUB_NO")
        private int bldgSubNo;
        @JsonProperty("POST5_NO")
        private String post5No;
        @JsonProperty("SIDO_NM")
        private String sidoNm;
        @JsonProperty("SIGU_NM")
        private String siguNm;
        @JsonProperty("DNG_NM")
        private String dngNm;
        @JsonProperty("RI_NM")
        private String riNm;
        @JsonProperty("ADDR_JIBUN")
        private String addrJibun;
        @JsonProperty("ADDR_ROAD")
        private String addrRoad;
        @JsonProperty("GRS80_X_BLDG_AVG")
        private String grs80XBldgAvg;
        @JsonProperty("GRS80_Y_BLDG_AVG")
        private String grs80YBldgAvg;
        @JsonProperty("WGS84_X_BLDG_AVG")
        private String wgs84XBldgAvg;
        @JsonProperty("WGS84_Y_BLDG_AVG")
        private String wgs84YBldgAvg;
    }

    @Getter
    public static class Paging {
        @JsonProperty("pageRow")
        private int pageRow;
        @JsonProperty("pageCount")
        private int pageCount;
        @JsonProperty("curPage")
        private int curPage;
        @JsonProperty("totalCount")
        private int totalCount;
        @JsonProperty("totalRecordCount")
        private int totalRecordCount;
    }
}

