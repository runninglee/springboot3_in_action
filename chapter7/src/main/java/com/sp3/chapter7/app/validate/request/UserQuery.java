package com.sp3.chapter7.app.validate.request;

import com.sp3.chapter7.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends BaseRequest {

    private String keywords;

    private int page = 1;

    private int pageSize = 30;
}
