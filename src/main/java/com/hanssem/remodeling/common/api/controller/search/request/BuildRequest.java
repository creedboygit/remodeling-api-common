package com.hanssem.remodeling.common.api.controller.search.request;

import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.common.model.PageAndSortRequest;
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
public class BuildRequest extends PageAndSortRequest {

    @Schema(name = "bldgMgmtNo", title = "빌딩관리번호", type = "String", example = "1138010700104170005021239")
    private String bldgMgmtNo;

    protected BuildRequest(int page, int size, List<Order> sorts) {
        super(page, size, sorts);
    }

    public void verify() {
        if (Objects.isNull(bldgMgmtNo) || bldgMgmtNo.trim().isEmpty()) {
            throw new CustomException(ResponseCode.INVALID_PARAMS);
        }
    }

}
