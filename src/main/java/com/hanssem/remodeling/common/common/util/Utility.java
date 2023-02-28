package com.hanssem.remodeling.common.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utility {

    public static StringJoiner stringWithBlank() {
        return new StringJoiner(" ");
    }

    public static String getJoinString(final Object... values) {
        return String.format("%s".repeat(values.length), values);
    }
}
