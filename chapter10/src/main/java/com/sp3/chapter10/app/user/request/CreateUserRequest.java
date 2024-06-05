package com.sp3.chapter10.app.user.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sp3.chapter10.common.BaseRequest;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserRequest extends BaseRequest {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull(message = "用户手机号不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "用户手机号格式不正确")
    private String mobile;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 16, message = "用户密码长度必须在6到16之间")
    private String password;

    @NotNull(message = "用户角色ID不为空")
    private int role_id;

    private boolean is_admin = false;

    private int status = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expired_at;

    private String ip;

    private String location;

}
