package com.hanssem.remodeling.common.api.repository.commoncode;

import static org.hibernate.annotations.QueryHints.*;

import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface CommoncodeValueRepository extends JpaRepository<CommoncodeValue, String> {

    @QueryHints({@QueryHint(name = COMMENT, value = "CommoncodeValueRepository.findByCodeId")})
    @Query("select v from CommoncodeValue  v where v.commoncode.codeId = :codeId order by v.codeValueSeq")
    List<CommoncodeValue> findByCodeId(@Param("codeId") String codeId);

}
