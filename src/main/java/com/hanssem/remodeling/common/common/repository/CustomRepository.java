package com.hanssem.remodeling.common.common.repository;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
public  class CustomRepository {
    private static OrderSpecifier orderByNull = new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);

    protected final BooleanExpression isNullContains(String value, Path expression) {
        return value == null || value.isEmpty() ? null : ((StringPath) expression).contains(value);
    }

    protected final BooleanExpression isNullLike(String value, Path expression) {
        return value == null ? null : ((StringPath) expression).like(value);
    }

    protected final BooleanExpression isNullEq(String value, Path expression) {
        return value == null ? null : ((StringPath) expression).eq(value);
    }

    protected final BooleanExpression isNullEq(Long value, Path expression) {
        return value == null || value == 0 ? null : ((NumberPath<Long>) expression).eq(value);
    }

    protected final BooleanExpression isNullEq(Integer value, Path expression) {
        return value == null || value == 0 ? null : ((NumberPath<Integer>) expression).eq(value);
    }

    protected final BooleanExpression isNullIn(List<String> values, Path expression) {
        return values == null ? null : ((StringPath) expression).in(values);
    }

    protected final BooleanExpression isNullNe(String value, Path expression) {
        return value == null ? null : ((StringPath) expression).ne(value);
    }

    protected final BooleanExpression isNullNe(Enum value, Path expression) {
        return value == null ? null : ((EnumPath) expression).ne(value);
    }

    protected final BooleanExpression isNullEnum(Collection<?> values, Path expression) {
        return values == null ? null : ((EnumPath) expression).eq(values);
    }

    protected final BooleanExpression isNullEnumIn(Collection<?> values, Path expression) {
        return values == null ? null : ((EnumPath) expression).in(values);
    }

    protected final BooleanExpression isNullNe(Long value, Path expression) {
        return value == null || value == 0 ? null : ((NumberPath<Long>) expression).ne(value);
    }

    protected final BooleanExpression isNullGt(LocalDateTime value, Path expression) {
        return value == null ? null : ((DateTimePath) expression).gt(value);
    }


    protected final BooleanExpression isTrueNe(boolean condition, Long value, Path expression) {
        return !condition ? null : isNullNe(value, expression);
    }

    protected OrderSpecifier orderByNull() {
        return orderByNull;
    }

}