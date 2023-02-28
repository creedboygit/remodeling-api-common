package com.hanssem.remodeling.common.api.controller.common;

import com.hanssem.remodeling.common.api.controller.common.request.CartCountRequest;
import com.hanssem.remodeling.common.api.controller.common.response.CartCountResponse;
import com.hanssem.remodeling.common.api.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Tag(name = "장바구니")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/commons/cart")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니 갯수 조회", description = "장바구니 갯수 조회<br><br>1. 회원 : custNo 파라미터에 custNo의 value 값을 넣어야 함<br>2. 비회원 : custNo 파라미터에 NO_LOGIN_COOKIE_USER_NO 쿠키에 들어있는 value 값을 넣어야 함", responses = {@ApiResponse(content = @Content(schema = @Schema()))})
//    @OnlyLogin
    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartCountResponse getCartCount(@Valid CartCountRequest request) {
        return cartService.getCartCount(request);
    }
}
