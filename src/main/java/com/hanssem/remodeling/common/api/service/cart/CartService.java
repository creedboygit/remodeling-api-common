package com.hanssem.remodeling.common.api.service.cart;

import com.hanssem.remodeling.common.api.controller.common.request.CartCountRequest;
import com.hanssem.remodeling.common.api.controller.common.response.CartCountResponse;
import com.hanssem.remodeling.common.api.repository.client.HanssemMallClient;
import com.hanssem.remodeling.common.api.service.cart.vo.MallCartCountVo;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.constant.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final HanssemMallClient hanssemMallClient;

//    private final AuthClaim authClaim;

    public CartCountResponse getCartCount(CartCountRequest request) {

        try {
            MallCartCountVo mallCartCountVo = hanssemMallClient.getHanssemMallCartCount(request);
            return CartCountResponse.builder().cartCnt(mallCartCountVo.getNaviInfoMap().getCartCnt()).build();
        } catch (Exception e) {
            throw new CustomException(ResponseCode.ERROR);
        }
    }
}
