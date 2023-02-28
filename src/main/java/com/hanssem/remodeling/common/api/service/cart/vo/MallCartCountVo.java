package com.hanssem.remodeling.common.api.service.cart.vo;

import com.hanssem.remodeling.common.api.service.common.vo.HanssemMallAPIBaseVo;
import com.hanssem.remodeling.common.common.error.CustomException;
import lombok.*;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MallCartCountVo {

    private int code;

    private String message;

    private NaviInfoMapResponse naviInfoMap;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class NaviInfoMapResponse {

        private int cartCnt;
        private int orderCnt;
    }
}
