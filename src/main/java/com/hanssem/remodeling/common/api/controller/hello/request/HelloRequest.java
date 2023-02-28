package com.hanssem.remodeling.common.api.controller.hello.request;


import com.hanssem.remodeling.common.common.model.MemberType;
import com.hanssem.remodeling.common.common.model.PageAndSortRequest;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Sort;

@Getter
@ToString
@ParameterObject
public final class HelloRequest extends PageAndSortRequest {

    private int id;
    private Integer id2;
    private boolean hello;
    private Boolean hello2;
    private long helloId;
    private Long helloId2;
    private Long [] helloId3;
    private int helloType;
    private String name;
    private MemberType memberType;
    private String [] test;
    private List<String> tests;
    private double aDouble;
    private Double aDoubleRef;
    private float aFloat;
    private Float aFloatRef;

    private TestEnum testEnum;
    private List<TestEnum> testEnumList;
    private TestEnum [] testEnumArr;


    protected HelloRequest(int page, int size, List<Sort.Order> sorts) {
        super(page, size, sorts);
    }

    public enum TestEnum {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

        TestEnum(int num) {
        }
    }

}
