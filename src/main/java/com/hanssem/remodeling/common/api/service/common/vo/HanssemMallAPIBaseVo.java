package com.hanssem.remodeling.common.api.service.common.vo;

import com.hanssem.remodeling.common.common.error.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HanssemMallAPIBaseVo {

    private int code;
    private String message;

    public void validateResponse() {
        if (this.code != HttpStatus.SC_OK) {
            throw CustomException.fromHanssemMallApi(code, message);
        }
    }
}