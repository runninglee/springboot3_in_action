package com.sp3.chapter7.app.validate.request;

import com.sp3.chapter7.app.validate.request.group.*;
import com.sp3.chapter7.common.BaseRequest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest extends BaseRequest {

    @NotBlank(message = "用户名不能为空",groups = NameGroup.class)
    private String name;

    @NotNull(message = "用户手机号不能为空",groups = MobileGroup.class)
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "用户手机号格式不正确",groups = MobileGroup.class)
    private String mobile;


    @NotNull(message = "用户身份证不能为空", groups = IdCardlGroup.class)
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$", message = "用户身份证格式不争取", groups = IdCardlGroup.class)
    private String idCard;

    @NotNull(message = "用户邮箱不能为空", groups = EmailGroup.class)
    @Email(message = "用户邮箱格式不正确", groups = EmailGroup.class)
    private String email;

    @NotNull(message = "用户密码不能为空", groups = PasswordGroup.class)
    @Size(min = 6, max = 16, message = "用户密码长度必须在6到16之间", groups = PasswordGroup.class)
    private String password;

    private boolean sex;

    @Positive(message = "年龄必须是正整数", groups = AgeGroup.class)
    private int age;

}
