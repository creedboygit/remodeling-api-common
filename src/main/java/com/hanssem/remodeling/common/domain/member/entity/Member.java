package com.hanssem.remodeling.common.domain.member.entity;

import com.hanssem.remodeling.common.common.model.MemberType;
import com.hanssem.remodeling.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String name;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MemberType type;

    private Member(final String name, final MemberType type) {
        this.name = name;
        this.type = type;
    }
    public static Member of(final String name, final MemberType type) {
        return new Member(name, type);
    }
}
