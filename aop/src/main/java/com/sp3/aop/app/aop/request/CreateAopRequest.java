package com.sp3.aop.app.aop.request;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateAopRequest extends BaseRequest {

    private String id;
}
