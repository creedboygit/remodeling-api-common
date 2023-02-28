package com.hanssem.remodeling.common.api.repository.member;

import static com.hanssem.remodeling.common.domain.member.entity.QMember.member;

import com.hanssem.remodeling.common.api.controller.member.request.SearchRequest;
import com.hanssem.remodeling.common.api.service.member.dto.MemberInfoDto;
import com.hanssem.remodeling.common.common.repository.CustomRepository;
import com.hanssem.remodeling.common.common.response.PageResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository("memberCustomRepository")
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl extends CustomRepository implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberInfoDto> getMembers() {
        return queryFactory.select(
            Projections.constructor(
                MemberInfoDto.class,
                member.memberId,
                member.name.as("newName"),
                member.type.stringValue()
                ))
            .from(member)
            .fetch();
    }

    @Override
    public PageResponse<MemberInfoDto> searchMembers(SearchRequest request) {
        BooleanBuilder builder = getMemberCondition(request);

        List<MemberInfoDto> content = queryFactory
            .select(
                Projections.constructor(
                MemberInfoDto.class,
                member.memberId,
                member.name.as("newName"),
                member.type.stringValue()
            ))
            .from(member)
            .where(builder)
            .orderBy(request.getOrderSpecifiers())
            .offset(request.getPageable().getOffset())
            .limit(request.getPageable().getPageSize())
            .fetch();

        long total = queryFactory
            .select(Wildcard.count)
            .from(member)
            .where(builder)
            .fetch().get(0);



        return PageResponse.of(content, request.getPageable(), total);
    }

    private BooleanBuilder getMemberCondition(SearchRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if (request.getType() != null) {
            builder.and(member.type.eq(request.getType()));
        }
        if (request.getName() != null) {
            builder.and(member.name.like(request.getName() + "%"));
        }

        return builder;
    }
}
