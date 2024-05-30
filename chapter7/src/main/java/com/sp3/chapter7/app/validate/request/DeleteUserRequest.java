package com.sp3.chapter7.app.validate.request;

import com.sp3.chapter7.app.validate.request.group.IdGroup;
import com.sp3.chapter7.common.BaseRequest;
import com.sp3.chapter7.util.rule.exist.Exist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest extends BaseRequest {

    @NotBlank(message = "请求参数异常")
    @Exist(message = "数据不存在!", table = "users", field = "id")
    private String id;

}
