package com.hanssem.remodeling.common.common.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConstants {

    private static String cdnUrl;

    @Value("${amazon.cdn-url}")
    public void setCdnUrl(String url) {
        cdnUrl = url;
    }

    public static String makeCdnUrl(String path) {
        if (path == null || path.isEmpty()) return null;
        return String.format("%s%s", cdnUrl, path);
    }
}

