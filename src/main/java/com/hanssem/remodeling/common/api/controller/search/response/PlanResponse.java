package com.hanssem.remodeling.common.api.controller.search.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanResponse {
    @Schema(title = "bldgMgmtNo", description = "", example = "")
    private String bldgMgmtNo;
    @Schema(title = "houseCd", description = "한샘 주택 DB 아파트코드", example = "")
    private String houseCd;
    @Schema(title = "bldgNm", description = "건물명", example = "마곡엠밸리11단지")
    private String bldgNm;
    @Schema(title = "sidoNm", description = "주소_시", example = "서울특별시")
    private String sidoNm;
    @Schema(title = "siguNm", description = "주소_구", example = "강서구")
    private String siguNm;
    @Schema(title = "dngNm", description = "주소_동", example = "마곡동")
    private String dngNm;
    @Schema(title = "riNm", description = "주소_리", example = "")
    private String riNm;
    @Schema(title = "lglSidoCd", description = "법정시도코드", example = "11")
    private String lglSidoCd;
    @Schema(title = "lglSiguCd", description = "법정시군구코드", example = "11500")
    private String lglSiguCd;
    @Schema(title = "lglDngCd", description = "법정동코드", example = "1150010500")
    private String lglDngCd;
    @Schema(title = "fpId", description = "도면ID(파일서버 검색용)", example = "")
    private String fpId;
    @Schema(title = "fpUrlKms", description = "", example = "")
    private String fpUrlKms;
    @Schema(title = "fpUrlHs", description = "", example = "")
    private String fpUrlHs;
    @Schema(title = "bldgCmpltDt", description = "사용승인일", example = "20160912")
    private String bldgCmpltDt;
    @Schema(title = "areaCommM2", description = "공급면적(m2)", example = "84.94")
    private float areaCommM2;
    @Schema(title = "areaCommM2Int", description = "공급면적(m2)", example = "84")
    private int areaCommM2Int;
    @Schema(title = "areaCommPn", description = "공급면적(평)", example = "25.123")
    private float areaCommPn;
    @Schema(title = "areaCommPnInt", description = "공급면적(평)", example = "25")
    private int areaCommPnInt;
    @Schema(title = "areaTp", description = "면적타입", example = "F")
    private String areaTp;
    @Schema(title = "areaPrvtM2", description = "전용면적(m2)", example = "59.99")
    private float areaPrvtM2;
    @Schema(title = "areaPrvtM2Int", description = "전용면적(m2)", example = "59")
    private int areaPrvtM2Int;
    @Schema(title = "areaPrvtPn", description = "전용면적(평)", example = "18.123")
    private float areaPrvtPn;
    @Schema(title = "areaPrvtPnInt", description = "전용면적(평)", example = "18")
    private int areaPrvtPnInt;
    @Schema(title = "hshldAll", description = "총세대수", example = "347")
    private int hshldAll;
    @Schema(title = "hshldTp", description = "타입별세대수", example = "14")
    private String hshldTp;
    @Schema(title = "roomCnt", description = "방 개수", example = "3")
    private int roomCnt;
    @Schema(title = "bathCnt", description = "욕실 개수", example = "2")
    private int bathCnt;
    @Schema(title = "entrcStrct", description = "현관구조", example = "계단식")
    private String entrcStrct;
    @Schema(title = "ldkStrct", description = "LDK 구조", example = "LDK")
    private String ldkStrct;
    @Schema(title = "wndStrct", description = "외창 구조", example = "11자")
    private String wndStrct;
    @Schema(title = "bay", description = "bay 수", example = "3")
    private int bay;
    @Schema(title = "kitSepYn", description = "", example = "")
    private String kitSepYn;

    public int getAreaCommPnInt() {
        if (Objects.nonNull(areaCommPn)) {
            return (int) areaCommPn;
        } else {
            return 0;
        }
    }

    public int getAreaPrvtPnInt() {
        if (Objects.nonNull(areaPrvtPn)) {
            return (int) areaPrvtPn;
        } else {
            return 0;
        }
    }

    public int getAreaCommM2Int() {
        if (Objects.nonNull(areaCommM2)) {
            return (int) areaCommM2;
        } else {
            return 0;
        }
    }

    public int getAreaPrvtM2Int() {
        if (Objects.nonNull(areaPrvtM2)) {
            return (int) areaPrvtM2;
        } else {
            return 0;
        }
    }
}
