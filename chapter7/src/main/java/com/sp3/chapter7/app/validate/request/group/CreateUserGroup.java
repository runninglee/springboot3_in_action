package com.sp3.chapter7.app.validate.request.group;

import jakarta.validation.GroupSequence;

@GroupSequence({NameGroup.class, MobileGroup.class, IdCardlGroup.class, EmailGroup.class, PasswordGroup.class, AgeGroup.class})
public interface CreateUserGroup {
}
