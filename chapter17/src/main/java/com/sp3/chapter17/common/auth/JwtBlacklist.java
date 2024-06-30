package com.sp3.chapter17.common.auth;


import java.util.HashSet;
import java.util.Set;

public class JwtBlacklist {
    private static final Set<String> blacklist = new HashSet<>();

    public static void add(String token) {
        blacklist.add(token);
    }

    public static boolean contains(String token) {
        return blacklist.contains(token);
    }
}