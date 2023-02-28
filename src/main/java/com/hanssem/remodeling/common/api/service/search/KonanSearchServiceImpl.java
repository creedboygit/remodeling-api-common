package com.hanssem.remodeling.common.api.service.search;

import com.hanssem.remodeling.common.api.controller.search.request.AddressRequest;
import com.hanssem.remodeling.common.api.controller.search.request.BuildRequest;
import com.hanssem.remodeling.common.api.controller.search.request.PlanRequest;
import com.hanssem.remodeling.common.api.controller.search.response.AddressResponse;
import com.hanssem.remodeling.common.api.controller.search.response.PlanResponse;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.response.PageResponse;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.konantech.ksf.client.CrzClient;
import com.konantech.ksf.client.SearchQuery;
import com.konantech.ksf.client.result.SearchResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KonanSearchServiceImpl implements KonanSearch {

    private final String SEARCH_HOUSE_SCENARIO = "SRCH_HOUSE";
    private final String SEARCH_FP_SCENARIO = "SRCH_HOUSE_FP";

    @Value("${konan.crz.host}")
    private  String crzhost;
    @Value("${konan.crz.port}")
    private  String crzport;
    @Value("${konan.crz.charset}")
    private  String crzcharset;

    /**
     * 주소검색
     */
    @Override
    public Page<AddressResponse> searchAddress(AddressRequest searchRequest) throws Exception {
        String query = searchRequest.getQuery();

        /** 검색엔진 검색 연결 */
        CrzClient crzclient = new CrzClient(crzhost, Integer.parseInt(crzport));
        if (crzcharset != null) {
            crzclient.setCharset(crzcharset);
        }

        /** 검색 쿼리 생성 */
        SearchQueryBuilder builder = new SearchQueryBuilder(SEARCH_HOUSE_SCENARIO, query);
        SearchQuery sq = builder.getSearchAddressQuery(searchRequest);
        log.debug("==> query : [ {} ], where [ {} ]", sq.getQuery(), sq.getWhereClause());

        /** 클라이언트 API 호출(검색요청) */
        SearchResultSet srs = crzclient.search(sq);

        /**
         * 검색 결과 response 처리
         */
        List<AddressResponse> responseList = new ArrayList<>();
        while (srs.next()) {
            AddressResponse build = getAddressResponse(srs);
            responseList.add(build);
        }

        return PageResponse.of(responseList, searchRequest.getPageable(), srs.getTotalCount());
    }


    @Override
    public Page<AddressResponse> searchAddressByBldgMgmtNo(BuildRequest request) throws Exception {
        String query = request.getBldgMgmtNo();

        /** 검색엔진 검색 연결 */
        CrzClient crzclient = new CrzClient(crzhost, Integer.parseInt(crzport));
        if (crzcharset != null) {
            crzclient.setCharset(crzcharset);
        }

        /** 검색 쿼리 생성 */
        SearchQueryBuilder builder = new SearchQueryBuilder(SEARCH_HOUSE_SCENARIO, query);
        SearchQuery sq = builder.getSearchBuildQuery(request);
        log.debug("==> query : [ {} ], where [ {} ]", sq.getQuery(), sq.getWhereClause());

        /** 클라이언트 API 호출(검색요청) */
        SearchResultSet srs = crzclient.search(sq);

        /**
         * 검색 결과 response 처리
         */
        List<AddressResponse> responseList = new ArrayList<>();
        while (srs.next()) {
            AddressResponse build = getAddressResponse(srs);
            responseList.add(build);
        }

        return PageResponse.of(responseList, request.getPageable(), srs.getTotalCount());
    }

    /**
     * 도면검색
     */
    @Override
    public Page<PlanResponse> searchBlueprint(PlanRequest searchRequest) throws Exception {
        String query = searchRequest.getBldgMgmtNo();

        CrzClient crzclient = new CrzClient(crzhost, Integer.parseInt(crzport));
        if (crzcharset != null) {
            crzclient.setCharset(crzcharset);
        }

        /** 검색 쿼리 생성 */
        SearchQueryBuilder builder = new SearchQueryBuilder(SEARCH_FP_SCENARIO, query);
        SearchQuery sq = builder.getSearchBlueprintQuery(searchRequest);
        log.debug("==> query : [ {} ], where [ {} ]", sq.getQuery(), sq.getWhereClause());

        /** 클라이언트 API 호출(검색요청) */
        SearchResultSet srs = crzclient.search(sq);

        /**
         * 검색 결과 response 처리
         */
        List<PlanResponse> responseList = new ArrayList<>();
        while (srs.next()) {
            PlanResponse build = getBlueprintResponse(srs);
            responseList.add(build);
        }
        if (responseList == null || responseList.isEmpty()) {
            throw new CustomException(ResponseCode.DATA_NOT_FOUND);
        }

        return PageResponse.of(responseList, searchRequest.getPageable(), srs.getTotalCount());
    }


    private AddressResponse getAddressResponse(SearchResultSet srs) {

        AddressResponse build = new AddressResponse();

        build.setHouseCd(srs.getString("HOUSE_CD"));
        build.setBldgMgmtNo(srs.getString("BLDG_MGMT_NO"));
        build.setBldgNm(srs.getString("BLDG_NM"));
        build.setAptYn(srs.getString("APT_YN"));
        build.setLglSidoCd(srs.getString("LGL_SIDO_CD"));
        build.setLglSiguCd(srs.getString("LGL_SIGU_CD"));
        build.setLglDngCd(srs.getString("LGL_DNG_CD"));
        build.setBunji(Integer.parseInt(srs.getString("BUNJI")));
        build.setHo(Integer.parseInt(srs.getString("HO")));
        build.setRoadCd(srs.getString("ROAD_CD"));
        build.setBldgMainNo(Integer.parseInt(srs.getString("BLDG_MAIN_NO")));
        build.setBldgSubNo(Integer.parseInt(srs.getString("BLDG_SUB_NO")));
        build.setPost5No(srs.getString("POST5_NO"));
        build.setSidoNm(srs.getString("SIDO_NM"));
        build.setSiguNm(srs.getString("SIGU_NM"));
        build.setDngNm(srs.getString("DNG_NM"));
        build.setRiNm(srs.getString("RI_NM"));
        build.setAddrJibun(srs.getString("ADDR_JIBUN"));
        build.setAddrRoad(srs.getString("ADDR_ROAD"));
        build.setGrs80XBldgAvg(srs.getString("GRS80_X_BLDG_AVG"));
        build.setGrs80YBldgAvg(srs.getString("GRS80_Y_BLDG_AVG"));
        build.setWgs84YBldgAvg(srs.getString("WGS84_X_BLDG_AVG"));
        build.setWgs84XBldgAvg(srs.getString("WGS84_Y_BLDG_AVG"));

        return build;
    }


    public PlanResponse getBlueprintResponse(SearchResultSet srs) {

        PlanResponse build = new PlanResponse();

        build.setHouseCd(srs.getString("HOUSE_CD"));
        build.setBldgMgmtNo(srs.getString("BLDG_MGMT_NO"));
        build.setBldgNm(srs.getString("BLDG_NM"));
        build.setSidoNm(srs.getString("SIDO_NM"));
        build.setSiguNm(srs.getString("SIGU_NM"));
        build.setDngNm(srs.getString("DNG_NM"));
        build.setRiNm(srs.getString("RI_NM"));
        build.setLglSidoCd(srs.getString("LGL_SIDO_CD"));
        build.setLglSiguCd(srs.getString("LGL_SIGU_CD"));
        build.setLglDngCd(srs.getString("LGL_DNG_CD"));
        build.setFpId(srs.getString("FP_ID"));
        build.setFpUrlKms(srs.getString("FP_URL_KMS"));
        build.setFpUrlHs(srs.getString("FP_URL_HS"));
        build.setBldgCmpltDt(srs.getString("BLDG_CMPLT_DT"));
        build.setAreaCommM2(Float.parseFloat(srs.getString("AREA_COMM_M2")));
        build.setAreaCommPn(Float.parseFloat(srs.getString("AREA_COMM_PN")));
        build.setAreaTp(srs.getString("AREA_TP"));
        build.setAreaPrvtM2(Float.parseFloat(srs.getString("AREA_PRVT_M2")));
        build.setAreaPrvtPn(Float.parseFloat(srs.getString("AREA_PRVT_PN")));
        build.setHshldAll(Integer.parseInt(srs.getString("HSHLD_ALL")));
        build.setHshldTp(srs.getString("HSHLD_TP"));
        build.setRoomCnt(Integer.parseInt(srs.getString("ROOM_CNT")));
        build.setBathCnt(Integer.parseInt(srs.getString("BATH_CNT")));
        build.setEntrcStrct(srs.getString("ENTRC_STRCT"));
        build.setLdkStrct(srs.getString("LDK_STRCT"));
        build.setWndStrct(srs.getString("WND_STRCT"));
        if (!srs.getString("BAY").isEmpty()) {
            build.setBay(Integer.parseInt(srs.getString("BAY")));
        }
        build.setKitSepYn(srs.getString("KIT_SEP_YN"));

        return build;
    }

}
