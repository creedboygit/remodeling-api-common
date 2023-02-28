package com.hanssem.remodeling.common.domain.commoncode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanssem.remodeling.common.domain.SystemEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_common_code_value")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommoncodeValue extends SystemEntity implements Serializable {

    @Id
    @Column(name = "CODE_VALUE_ID")
    private String codeValueId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_ID")
    private Commoncode commoncode;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column(name = "CODE_VALUE_NAME")
    private String codeValueName;

    @Column(name = "CODE_VALUE_DESCRIPTION")
    private String description;

    @Column(name = "CODE_VALUE_SEQ")
    private int codeValueSeq;

    @Column(name = "VALID_END_DATETIME")
    private LocalDateTime validEndDatetime;

    @Column(name = "UPPER_CODE_VALUE_ID")
    private String upperCodeValueId;

    @Column(name = "ETC_VALUE1")
    private String etcValue1;

    @Column(name = "ETC_VALUE2")
    private String etcValue2;

    //==연관관계 메서드==//
    public void setCommoncode(Commoncode commoncode) {
        this.commoncode = commoncode;
    }
}
