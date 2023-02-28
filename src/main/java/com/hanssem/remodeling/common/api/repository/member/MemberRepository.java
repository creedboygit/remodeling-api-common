package com.hanssem.remodeling.common.api.repository.member;

import com.hanssem.remodeling.common.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(final String name);
}
