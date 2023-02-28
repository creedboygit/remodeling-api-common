package com.hanssem.remodeling.common.api.service.search;

import com.hanssem.remodeling.common.api.controller.search.request.AddressRequest;
import com.hanssem.remodeling.common.api.controller.search.request.BuildRequest;
import com.hanssem.remodeling.common.api.controller.search.request.PlanRequest;
import com.konantech.ksf.client.QueryBuilder;
import com.konantech.ksf.client.SearchQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

@Slf4j
@SuppressWarnings("unused")
public class SearchQueryBuilder {

    private final String QUOTES = "\"";
    private final String EQUAL = "=";
    private final List<String> searchFields = Arrays.asList("ADDR_JIBUN", "ADDR_ROAD");
    private final List<String> dictionaryFields = Arrays.asList("BLDG_NM");
    private final List<String> exactFields = Arrays.asList("BLDG_MGMT_NO");


    private QueryBuilder qb;
    private String scenario;
    private String query;

    public SearchQueryBuilder(String scenario, String query) {
        this.scenario = scenario;
        this.query = getAndWhereKeyword(query);
        this.qb = new QueryBuilder();
    }

    public void setScenario(String s) {
        this.scenario = s;
    }

    public SearchQuery getSearchAddressQuery(AddressRequest searchRequest) throws Exception {

        SearchQuery sq = new SearchQuery(scenario, query);

        /****검색 기본 조건 (검색을 하거나, 필터 리스트를 조회하거나, 필터를 선택하거나 했을때 기본적으로 들어가는 where조건) *****/
        String whereString = "";
        String keyword = query;
        StringBuffer keywordWhereClause = new StringBuffer();

        whereString = keywordWhereClause.append("txt_idx").append(EQUAL)
            .append(QUOTES).append(query).append(QUOTES)
//            .append(" NATURAL SYNONYM ")
            .append(" ALLWORDTHRUINDEX SYNONYM ")
//            .append(" aliasing HOUSE_CD as HOUSE_CD_T, HOUSE_CD as HOUSE_CD_F (HOUSE_CD_T != null or HOUSE_CD_F = null)")
            .toString();

        sq.setWhereClause(whereString);

//        sq.setSortingClause("APT_YN DESC, $relevance desc");
//        sq.setSortingClause("APT_YN DESC");
        sq.setOffset(searchRequest.getPage() * sq.getLimit()); // 페이지번호(default 0)
        sq.setLimit(searchRequest.getSize()); // 페이지사이즈

        return sq;
    }


    public SearchQuery getSearchBuildQuery(BuildRequest request) {
        SearchQuery sq = new SearchQuery(scenario, query);

        String whereString = "";
        query = request.getBldgMgmtNo();
        StringBuffer keywordWhereClause = new StringBuffer();

        whereString = keywordWhereClause.append("BLDG_MGMT_NO").append(EQUAL)
            .append(QUOTES).append(query).append(QUOTES)
            .toString();

        sq.setWhereClause(whereString);

        sq.setOffset(request.getPage() * sq.getLimit()); // 페이지번호(default 0)
        sq.setLimit(request.getSize()); // 페이지사이즈

        return sq;
    }

    public SearchQuery getSearchAddressQuery2(AddressRequest searchRequest) throws Exception {

        SearchQuery sq = new SearchQuery(scenario, query);
        sq.setLimit(searchRequest.getSize()); // 페이지사이즈
        sq.setOffset(searchRequest.getPage() * sq.getLimit()); // 페이지번호(default 0)
        sq.setSortingClause("$matchfield(HOUSE_CD_T, HOUSE_CD_F) desc, APT_YN DESC, $relevance desc");

        /****검색 기본 조건 (검색을 하거나, 필터 리스트를 조회하거나, 필터를 선택하거나 했을때 기본적으로 들어가는 where조건) *****/
        String whereString = "";

        String keyword = query;
        StringBuffer keywordWhereClause = new StringBuffer();

        //형태소분석 적용 케이스
        for (int i = 0; i < searchFields.size(); i++) {
            String field = searchFields.get(i);
            keywordWhereClause.append(field).append(EQUAL).append(QUOTES).append(keyword).append(QUOTES);
            keywordWhereClause.append(" ");
            keywordWhereClause.append(lastCommand(i+1, searchFields.size()));
        }

        //연산자
        if (searchFields.size() > 0 && dictionaryFields.size() > 0) {
            keywordWhereClause.append(" OR ");
        }

        //사전 적용 케이스
        for (int i = 0; i < dictionaryFields.size(); i++) {
            String field = dictionaryFields.get(i);
            keywordWhereClause.append(field).append(EQUAL).append(QUOTES).append(keyword).append(QUOTES);
            keywordWhereClause.append(" ALLSTRING SYNONYM ");
            keywordWhereClause.append(lastCommand(i+1, dictionaryFields.size()));
        }

        //연산자
        if (dictionaryFields.size() > 0) {
            keywordWhereClause.append(" OR ");
        }
        if (StringUtils.isNotEmpty(query)) {
            keywordWhereClause.append("BLDG_MGMT_NO").append(EQUAL).append(QUOTES).append(keyword).append(QUOTES);
            keywordWhereClause.append(" ALLSTRING ");
            whereString = keywordWhereClause.toString();
        }

        sq.setWhereClause(whereString);

        return sq;
    }

    public SearchQuery getSearchBlueprintQuery(PlanRequest searchRequest) {
        SearchQuery sq = new SearchQuery(scenario, query);

        /****검색 기본 조건 (검색을 하거나, 필터 리스트를 조회하거나, 필터를 선택하거나 했을때 기본적으로 들어가는 where조건) *****/
        String whereString = "";

        String houseCd = searchRequest.getHouseCd() != null ? searchRequest.getHouseCd().trim() : Strings.EMPTY;
        String bldgMgmtNo = searchRequest.getBldgMgmtNo() != null ? searchRequest.getBldgMgmtNo().trim() : Strings.EMPTY;
        StringBuffer keywordWhereClause = new StringBuffer();

        if (!houseCd.isEmpty()) {
            keywordWhereClause.append("HOUSE_CD").append(EQUAL).append(QUOTES).append(houseCd).append(QUOTES);
        }
        if (!keywordWhereClause.isEmpty() && !bldgMgmtNo.isEmpty()) {
            keywordWhereClause.append(" AND ");
        }
        if (!bldgMgmtNo.isEmpty()) {
            keywordWhereClause.append("BLDG_MGMT_NO").append(EQUAL).append(QUOTES).append(bldgMgmtNo).append(QUOTES);
        }

        whereString = keywordWhereClause.toString();

        sq.setWhereClause(whereString);
        sq.setLimit(searchRequest.getSize()); // 페이지사이즈
        sq.setOffset(searchRequest.getPage() * sq.getLimit()); // 페이지번호(default 0)

        return sq;
    }

    private String lastCommand(int i, int size) {
        return (i < size) ? " OR " : "";
    }

    private String getAndWhereKeyword(String searchKeyStr) {
        if (Objects.isNull(searchKeyStr) || searchKeyStr.isEmpty()) {
            return "";
        }
        if (searchKeyStr.indexOf(" + ") < 0) {
            searchKeyStr = searchKeyStr.replaceAll(" ", " & ").replaceAll(" and ", " & ").replaceAll(" AND ", " & ");
//TODO replaceTextToHtml 처리하기
//            searchKeyStr = WebUtil.replaceTextToHtml(searchKeyStr).replaceAll("\'", "");
            searchKeyStr = searchKeyStr.replaceAll(" & & & ", " & ");
        }

        return searchKeyStr;
    }


}
