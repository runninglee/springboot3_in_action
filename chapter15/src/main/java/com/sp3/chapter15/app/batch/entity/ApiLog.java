package com.sp3.chapter15.app.batch.entity;

import com.sp3.chapter15.common.entity.BaseEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "api_logs")
public class ApiLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uuid;

    @Column
    private String category;

    @Column
    private String task;

    @Column
    private String name;

    @Column
    private String rel_type;

    @Column
    private String rel_id;

    @Column
    private String host;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Object header;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Object param;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Object response;

    @PrePersist
    protected void onCreate() {
        uuid = UUID.randomUUID().toString().replace("-", "");
        category = "拉取";
    }
}
