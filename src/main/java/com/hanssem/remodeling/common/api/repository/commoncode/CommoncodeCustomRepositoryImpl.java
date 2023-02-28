package com.hanssem.remodeling.common.api.repository.commoncode;

import static com.hanssem.remodeling.common.domain.commoncode.entity.QCommoncode.commoncode;
import static com.hanssem.remodeling.common.domain.commoncode.entity.QCommoncodeValue.commoncodeValue;

import com.hanssem.remodeling.common.common.repository.CustomRepository;
import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommoncodeCustomRepositoryImpl extends CustomRepository implements CommoncodeCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommoncodeValue> findCommoncodeValues(String request) {
        BooleanBuilder builder = makeCommoncodeValueQueryBuilder(request);

        List<CommoncodeValue> results = queryFactory
            .select(commoncodeValue)
            .from(commoncode)
            .join(commoncode.children, commoncodeValue)
            .where(builder)
            .orderBy(commoncodeValue.codeValueSeq.asc())
            .fetch();

        return results;
    }

    private BooleanBuilder makeCommoncodeValueQueryBuilder(String request) {
        BooleanBuilder builder = new BooleanBuilder();

//        builder.and(isNullGt(LocalDateTime.now(), commoncode.validEndDatetime));
//        builder.and(isNullGt(LocalDateTime.now(), commoncodeValue.validEndDatetime));
        builder.and(isNullEq(request, commoncode.codeName));

        return builder;
    }
}
