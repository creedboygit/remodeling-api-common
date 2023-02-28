package com.hanssem.remodeling.common.admin.repository.commoncode;

import com.hanssem.remodeling.common.domain.commoncode.entity.Commoncode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommoncodeAdminRepository extends JpaRepository<Commoncode, String> {

    @Query("select c.codeId from Commoncode c where c.codeName = :upperCodeName")
    String findByCodeName(@Param("upperCodeName") String upperCodeName);

    @Query("select c from Commoncode c where c.codeName = :codeName")
    Commoncode findCommoncode(@Param("codeName") String codeName);

}
