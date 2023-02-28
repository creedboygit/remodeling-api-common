package com.hanssem.remodeling.common.api.controller.member.request;

import com.hanssem.remodeling.common.common.model.MemberType;
import com.hanssem.remodeling.common.common.model.PageAndSortRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Order;

@Schema(name = "SearchRequest", description = "회원 검색용 데이터")
@Getter
@Setter
public class SearchRequest extends PageAndSortRequest {
    @NotBlank(message = "값을 입력해주세요.")
    @Schema(name = "name", title = "이름", type = "String", example = "iceman")
    private String name;

    @Schema(name = "type", title = "종류", type = "co.edithome.appname.domain.enums.MemberType", allowableValues = "(ADMIN, MEMBER, BUSINESS_MEMBER)", example = "MEMBER")
    private MemberType type;

    protected SearchRequest(int page, int size, List<Order> sorts) {
        super(page, size, sorts);
    }
}
