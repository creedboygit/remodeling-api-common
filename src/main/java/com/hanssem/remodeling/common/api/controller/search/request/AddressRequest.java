package com.hanssem.remodeling.common.api.controller.search.request;

import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.model.PageAndSortRequest;
import com.hanssem.remodeling.common.constant.AddressSearchType;
import com.hanssem.remodeling.common.constant.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Sort.Order;

@Getter
@Setter
@ParameterObject
public class AddressRequest extends PageAndSortRequest {
    @Schema(name = "query", title = "검색어", type = "String", example = "강남구")
    private String query;

    @Schema(name = "searchType", title = "searchType", type = "com.hanssem.remodeling.common.constant.AddressSearchType", example = "GOVERNMENT")
    private AddressSearchType searchType;

    protected AddressRequest(int page, int size, List<Order> sorts) {
        super(page, size, sorts);
    }

    public void verify() {
        if (Objects.isNull(query) || query.trim().isEmpty()) {
            throw new CustomException(ResponseCode.INVALID_PARAMS);
        }
        if (Objects.isNull(searchType)) {
            searchType = AddressSearchType.GOVERNMENT;
        }
    }

    public void cleansing() {
        //검색어 필터링(<, >, \, &)
        String[] excludeCharArr = {"<", ">", "\\\\", "&"};
        for(String excludeChar : excludeCharArr) {
            query = query.replaceAll(excludeChar, "");
        }

    }
}
