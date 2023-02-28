package com.hanssem.remodeling.common.common.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CdnPath  {
    private final String value;
    
    public CdnPath(String value) {
    	this.value = value;
    }
}
