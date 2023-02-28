package com.hanssem.remodeling.common.common.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthClaim implements Serializable {

    private long custNo;
    private long intMbsCustNo;
    private String accessToken;
}
