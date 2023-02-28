package com.hanssem.remodeling.common.api.controller.common.request;

import com.hanssem.remodeling.common.common.model.PaginationRequest;
import com.hanssem.remodeling.common.constant.ScrapTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ParameterObject
public class ScrapRequest extends PaginationRequest {
    @NotBlank
    @Schema(title = "스크랩타입", description = "ALL, STORE_GOODS, REMODELING_GOODS, CONSTRUCTION_CASE, IMAGE, MAGAZINE, SHOP", required = true)
    private ScrapTypeCode scrapTypeCode;

    public ScrapRequest(int page, int size, List<Sort.Order> sorts) {
        super(page, size, sorts);
    }
}
