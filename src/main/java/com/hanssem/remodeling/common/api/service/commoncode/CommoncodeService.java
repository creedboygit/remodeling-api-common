package com.hanssem.remodeling.common.api.service.commoncode;

import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeGroupResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeValueResponse;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface CommoncodeService {

    @Cacheable(cacheManager = "baseCacheManager", cacheNames = "findCommoncodeList", key = "")
    List<CommoncodeResponse> findCommoncodeList();

    @Cacheable(cacheManager = "baseCacheManager", cacheNames = "findCommoncodeValues", key = "#code")
    List<CommoncodeValueResponse> findCommoncodeValues(String code);

    @Cacheable(cacheManager = "baseCacheManager", cacheNames = "findGroupList", key = "#groupName")
    List<CommoncodeGroupResponse> findGroupList(String groupName);

    @CacheEvict(cacheManager = "baseCacheManager", cacheNames = "*", allEntries = true)
    void clearCacheAll();

}
