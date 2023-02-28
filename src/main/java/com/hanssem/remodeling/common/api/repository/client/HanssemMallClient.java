package com.hanssem.remodeling.common.api.repository.client;

import com.hanssem.remodeling.common.api.controller.common.request.CartCountRequest;
import com.hanssem.remodeling.common.api.service.cart.vo.MallCartCountVo;
import com.hanssem.remodeling.common.api.service.scrap.mapper.dto.AddScrapExternalDto;
import com.hanssem.remodeling.common.api.service.scrap.mapper.dto.DefaultScrapExternalDto;
import com.hanssem.remodeling.common.common.config.HanssemMallFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall-api", url = "${feign.baseUrl.mall-api}", configuration = HanssemMallFeignConfiguration.class)
public interface HanssemMallClient {

    @GetMapping(value = "${feign.client.config.mall-api.end-points.get-scraps}", produces = "application/json")
    String getScraps(@RequestParam(value = "curPage", defaultValue = "1") int curPage, @RequestParam(value = "pageRow", defaultValue = "100") int pageRow, @RequestParam(value = "wishGbnCd", defaultValue = "HI") String scrapType);

    @PostMapping(value = "${feign.client.config.mall-api.end-points.add-scraps}")
    String addScraps(@SpringQueryMap final AddScrapExternalDto dto);

    @DeleteMapping(value = "${feign.client.config.mall-api.end-points.delete-scraps}")
    String deleteScraps(@SpringQueryMap final DefaultScrapExternalDto dto);

    @GetMapping(value = "${feign.client.config.mall-api.end-points.exist-scraps}")
    String existScraps(@SpringQueryMap final DefaultScrapExternalDto dto);

    @GetMapping(value = "${feign.client.config.mall-api.end-points.get-address}")
    String getAddress(
        @RequestParam(value = "curPage", defaultValue = "1") int curPage,
        @RequestParam(value = "pageRow", defaultValue = "100") int pageRow,
        @RequestParam(value = "searchType", defaultValue = "GOVERMENT") String searchType,
        @RequestParam(value = "searchKey", defaultValue = "") String searchKey);

    @GetMapping(value = "${feign.client.config.mall-api.end-points.get-blueprints}")
    String getBlueprint(
        @RequestParam(value = "curPage", defaultValue = "1") int curPage,
        @RequestParam(value = "pageRow", defaultValue = "100") int pageRow,
        @RequestParam(value = "houseCd", defaultValue = "") String houseCd,
        @RequestParam(value = "bldgMgmtNo", defaultValue = "") String bldgMgmtNo);

    /**
     * 장바구니 갯수 조회
     */
    @PostMapping(value = "/API/v4/common/custNaviInfo")
    MallCartCountVo getHanssemMallCartCount(@SpringQueryMap CartCountRequest request);
}
