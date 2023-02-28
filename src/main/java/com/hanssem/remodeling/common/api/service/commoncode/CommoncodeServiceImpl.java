package com.hanssem.remodeling.common.api.service.commoncode;

import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeGroupResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeResponse;
import com.hanssem.remodeling.common.api.controller.commoncode.response.CommoncodeValueResponse;
import com.hanssem.remodeling.common.api.repository.commoncode.CommoncodeCustomRepository;
import com.hanssem.remodeling.common.api.repository.commoncode.CommoncodeRepository;
import com.hanssem.remodeling.common.api.service.commoncode.mapper.CommoncodeMapper;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.hanssem.remodeling.common.domain.commoncode.entity.Commoncode;
import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommoncodeServiceImpl implements CommoncodeService {

    private final CommoncodeRepository commoncodeRepository;
    private final CommoncodeCustomRepository commoncodeQueryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommoncodeResponse> findCommoncodeList() {
        List<Commoncode> commoncodeList = commoncodeRepository.findAll().stream()
            .filter(x -> x.getValidEndDatetime().isAfter(LocalDateTime.now()))
            .collect(Collectors.toList());
        return CommoncodeMapper.INSTANCE.toCommoncodeResponseList(commoncodeList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommoncodeValueResponse> findCommoncodeValues(String code) {
        Commoncode commoncode = commoncodeRepository.findByName(code);
        if (!Objects.nonNull(commoncode)) {
            throw new CustomException(ResponseCode.NOT_FOUND);
        }
        if (commoncode.getValidEndDatetime().isBefore(LocalDateTime.now())) {
            throw new CustomException(ResponseCode.INVALID_END_DATETIME);
        }
        List<CommoncodeValue> commoncodeValues = commoncodeQueryRepository.findCommoncodeValues(code)
            .stream()
            .filter(x -> x.getValidEndDatetime().isAfter(LocalDateTime.now()))
            .collect(Collectors.toList());
        return CommoncodeMapper.INSTANCE.toResponseList(commoncodeValues);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommoncodeGroupResponse> findGroupList(String groupName) {
        List<Commoncode> commoncodeList = commoncodeRepository.findUpperCode(groupName)
            .stream()
            .filter(x -> x.getValidEndDatetime().isAfter(LocalDateTime.now()))
            .collect(Collectors.toList());;
        for (Commoncode commoncode : commoncodeList) {
            List<CommoncodeValue> children = commoncode.getChildren()
                .stream().filter(x -> x.getValidEndDatetime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
            commoncode.setChildren(children);
        }

        return CommoncodeMapper.INSTANCE.toGroupResponseList(commoncodeList);
    }

    @Override
    public void clearCacheAll() {

    }
}
