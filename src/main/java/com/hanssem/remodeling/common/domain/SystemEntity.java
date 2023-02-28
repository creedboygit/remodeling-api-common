package com.hanssem.remodeling.common.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SystemEntity {

    @Column(name = "SYSTEM_CREATE_DATETIME")
    @CreatedDate
    private LocalDateTime systemCreateDatetime;

    @Column(name = "SYSTEM_CONSTRUCTOR_ID")
    private String systemConstructorId;

    @LastModifiedDate
    @Column(name = "SYSTEM_UPDATE_DATETIME")
    private LocalDateTime systemUpdateDatetime;

    @Column(name = "SYSTEM_UPDATER_ID")
    private String systemUpdaterId;
}
