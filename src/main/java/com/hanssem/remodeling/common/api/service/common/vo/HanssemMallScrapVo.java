package com.hanssem.remodeling.common.api.service.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class HanssemMallScrapVo extends HanssemMallAPIBaseVo {

    private Paging paging;
    private List<Scrap> data;

    private HanssemMallScrapVo(
            @JsonProperty(value = "code") int code,
            @JsonProperty(value = "message") String message,
            @JsonProperty(value = "pagingMap") Paging paging,
            @JsonProperty(value = "wishList") List<Scrap> data) {
        super(code, message);
        this.paging = paging;
        this.data = data;
    }

    public List<Scrap> getData() {
        super.validateResponse();
        return data;
    }

    @Getter
    public static class Scrap {
        @JsonProperty("WishGbnCd")
        private String type;
        @Setter
        @JsonProperty("TargetNo")
        private int id;
        @JsonProperty("TargetSubNo")
        private int subId;
        @JsonProperty("Title")
        private String title;
        @JsonProperty("DetailUrl")
        private String contentsUrl;
        @JsonProperty("ImgUrl")
        private String imageUrl;
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
    }
}

