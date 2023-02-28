package com.hanssem.remodeling.common.api.repository.commoncode;

import static org.hibernate.annotations.QueryHints.COMMENT;

import com.hanssem.remodeling.common.domain.commoncode.entity.Commoncode;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface CommoncodeRepository extends JpaRepository<Commoncode, String> {

    @QueryHints({@QueryHint(name = COMMENT, value = "CommoncodeRepository.findAll")})
    List<Commoncode> findAll();

    @QueryHints({@QueryHint(name = COMMENT, value = "CommoncodeRepository.findByName")})
    @Query("select c from Commoncode c where c.codeName = :codeName")
    Commoncode findByName(@Param("codeName") String codeName);

    @QueryHints({@QueryHint(name = COMMENT, value = "CommoncodeRepository.findUpperCode")})
    @Query("select c from Commoncode c where c.parent.codeName = :codeName")
    List<Commoncode> findUpperCode(@Param("codeName") String codeName);

}
