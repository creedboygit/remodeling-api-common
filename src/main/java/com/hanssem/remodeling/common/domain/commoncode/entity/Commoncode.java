package com.hanssem.remodeling.common.domain.commoncode.entity;

import com.hanssem.remodeling.common.domain.SystemEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_common_code")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Commoncode extends SystemEntity implements Serializable {

    @Id
    @Column(name = "CODE_ID")
    private String codeId;

    @Column(name = "STANDARD_CLASS_ID")
    private String standardClassId;

    @Column(name = "CODE_TYPE_CODE")
    private String codeTypeCode;

    @Column(name = "CODE_NAME")
    private String codeName;

    @Column(name = "CODE_KOR_NAME")
    private String codeKorName;

    @Column(name = "CODE_DESCRIPTION")
    private String codeDescription;

    @Column(name = "VALID_END_DATETIME")
    private LocalDateTime validEndDatetime;

    @Column(name = "UPPER_CODE_ID")
    private String upperCodeId;

    @Column(name = "ETC1")
    private String etc1;

    @Column(name = "ETC2")
    private String etc2;

    @Column(name = "LIST_CODE_TABLE_NAME")
    private String tableName;

    @Column(name = "LIST_CODE_TABLE_COLUMN")
    private String tableColumn;

    @Column(name = "OUTSIDE_CODE_ORIGIN_CONTENT")
    private String outsideCodeOriginContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_CODE_ID", referencedColumnName = "CODE_ID", insertable = false, updatable = false)
    private Commoncode parent;

    @Setter
    @OneToMany(mappedBy = "commoncode", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("codeValueSeq asc")
    @Builder.Default
    private List<CommoncodeValue> children = new ArrayList<>();

    //==연관관계 메서드==//
    public void association() {
        for (CommoncodeValue commoncodeValue : children) {
            commoncodeValue.setCommoncode(this);
        }
    }

    public void clearChildren() {
        children.clear();
    }
}