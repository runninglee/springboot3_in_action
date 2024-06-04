package com.sp3.chapter9.app.user.request;

import com.sp3.chapter9.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends BaseRequest {

    private String mobile;

    private String keywords;

    private int page = 1;

    private int pageSize = 30;

}
