package com.hanssem.remodeling.common.api.repository.commoncode;

import static org.hibernate.annotations.QueryHints.COMMENT;

import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.QueryHints;

public interface CommoncodeCustomRepository {

    @QueryHints({@QueryHint(name = COMMENT, value = "CommoncodeCustomRepository.findCommoncodeValues")})
    List<CommoncodeValue> findCommoncodeValues(String request);
}
