package com.sp3.chapter10.app.user.entity;

import com.sp3.chapter10.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String mobile;

    private String password;

    private String avatar;

    private int role_id;

    private boolean is_admin;

    private String ip;

    private String location;

    private String keywords;

    @PrePersist
    protected void onCreate() {
        is_admin = false;
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        keywords = username + mobile;
    }

    @PreUpdate
    protected void onUpdate() {
        keywords = username + mobile;
    }

}
