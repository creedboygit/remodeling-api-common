package com.hanssem.remodeling.common.api.service.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallAPIBaseVo;
import java.util.List;
import lombok.Getter;

@Getter
public class HanssemMallBlueprintVo extends HanssemMallAPIBaseVo {

    private Paging paging;
    private List<Blueprint> data;

    private HanssemMallBlueprintVo(
        @JsonProperty(value = "code") int code,
        @JsonProperty(value = "message") String message,
        @JsonProperty(value = "pagingMap") Paging paging,
        @JsonProperty(value = "apartTypeList") List<Blueprint> data) {
        super(code, message);
        this.paging = paging;
        this.data = data;
    }

    public List<Blueprint> getData() {
        super.validateResponse();
        return data;
    }

    @Getter
    public static class Blueprint {

        @JsonProperty("AREA_COMM_M2")
        private String areaCommM2; //float
        @JsonProperty("AREA_COMM_M2_STR")
        private String areaCommM2Str; //float
        @JsonProperty("AREA_COMM_PN")
        private String areaCommPn; //int
        @JsonProperty("AREA_PRVT_M2")
        private String areaPrvtM2; //float
        @JsonProperty("AREA_PRVT_PN")
        private String areaPrvtPn; //float
        @JsonProperty("AREA_TP")
        private String areaTp; //int
        @JsonProperty("AREA_TP_STR")
        private String areaTpStr;
        @JsonProperty("BATH_CNT")
        private String bathCnt; //int
        @JsonProperty("BAY")
        private String bay; //int
        @JsonProperty("BLDG_CMPLT_DT")
        private String bldgCmpltDt;
        @JsonProperty("BLDG_MGMT_NO")
        private String bldgMgmtNo;
        @JsonProperty("BLDG_NM")
        private String bldgNm;
        @JsonProperty("DNG_NM")
        private String dngNm;
        @JsonProperty("ENTRC_STRCT")
        private String entrcStrct;
        @JsonProperty("FP_ID")
        private String fpId;
        @JsonProperty("FP_URL_HS")
        private String fpUrlHs;
        @JsonProperty("FP_URL_KMS")
        private String fpUrlKms;
        @JsonProperty("HOUSE_CD")
        private String houseCd;
        @JsonProperty("HOUSE_ID")
        private String houseId;
        @JsonProperty("HSHLD_ALL")
        private String hshldAll; //int
        @JsonProperty("HSHLD_TP")
        private String hshldTp;
        @JsonProperty("KIT_SEP_YN")
        private String kitSepYn;
        @JsonProperty("LDK_STRCT")
        private String ldkStrct;
        @JsonProperty("")
        private String lglDngCd;
        @JsonProperty("LGL_DNG_CD")
        private String lglSidoCd;
        @JsonProperty("LGL_SIGU_CD")
        private String lglSiguCd;
        @JsonProperty("RI_NM")
        private String riNm;
        @JsonProperty("ROOM_CNT")
        private String roomCnt; //int
        @JsonProperty("SIDO_NM")
        private String sidoNm;
        @JsonProperty("SIGU_NM")
        private String siguNm;
        @JsonProperty("WND_STRCT")
        private String wndStrct;

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

