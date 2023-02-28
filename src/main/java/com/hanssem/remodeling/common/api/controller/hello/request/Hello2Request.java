package com.hanssem.remodeling.common.api.controller.hello.request;


import com.hanssem.remodeling.common.common.model.PaginationRequest;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public final class Hello2Request extends PaginationRequest {

    private String helloType;
    private int id;
    private Integer id2;
    private boolean hello;
    private Boolean hello2;
    private long helloId;
    private Long helloId2;
    private Long [] helloId3;

    protected Hello2Request(int page, int size, List<Sort.Order> sorts) {
        super(page, size, sorts);
    }
}
