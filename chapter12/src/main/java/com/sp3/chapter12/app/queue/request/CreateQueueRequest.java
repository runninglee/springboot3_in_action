package com.sp3.chapter12.app.queue.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQueueRequest implements Serializable {

    private long user_id;

    private String uuid;

    public String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
