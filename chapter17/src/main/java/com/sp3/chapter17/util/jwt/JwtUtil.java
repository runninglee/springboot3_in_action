package com.sp3.chapter17.util.jwt;

import com.sp3.chapter17.util.sqids.SqidsUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
@Data
public class JwtUtil {

    @Value("${app.jwt.header:Authorization}")
    private String header;

    @Value("${app.jwt.tokenPrefix:Bearer}")
    private String tokenPrefix;

    @Value("${app.jwt.secret:nLEIvvistMiuWGT3daIgL7ZsYCffFQ7lnd3Z2JxN6eOTQcQmHJRbTgECzezbVFeE}")
    private String secret;

    @Value("${app.jwt.expireTime:5}")
    private Long expireTime;

    private SecretKey key;

    private Date expiredAt;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getToken(String uid) {
        expiredAt = new Date(System.currentTimeMillis() + expireTime * 60 * 10000);
        return this.tokenPrefix + " " + Jwts.builder().header().type("JWT")
                .and()
                .issuer("https://ju-lan.com/api/login")
                .id(UUID.randomUUID().toString().replace("-", ""))
                .notBefore(new Date())
                .subject(SqidsUtil.encode(1L))
                .signWith(key)
                .issuedAt(new Date())
                .expiration(expiredAt)
//                .claims(Map.of("id", 1, "name", "HuiLee"))
                .compact();
    }

    public io.jsonwebtoken.Claims parse(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            return null;
        }
    }

    public Long getSubject(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            return SqidsUtil.decode(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
