package com.sp3.chapter17.util.sqids;

import org.sqids.Sqids;

import java.util.Collections;

public class SqidsUtil {
    private static final Sqids sqids = Sqids
            .builder()
            .alphabet("abcdefghijklmnopqrstuvwxyz")
            .minLength(10)
            .build();

    public static String encode(Long id) {
        return sqids.encode(Collections.singletonList(id));
    }

    public static Long decode(String sqid) {
        return sqids.decode(sqid).get(0);
    }
}