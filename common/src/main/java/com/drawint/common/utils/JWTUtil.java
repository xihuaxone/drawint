package com.drawint.common.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static String sign(Map<String, Object> payload) {
        return sign(payload, null);
    }

    public static String sign(Map<String, Object> payload, Date expireAt) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .addClaims(payload)
                .signWith(SignatureAlgorithm.RS256, SecretKeyUtil.getPrivateKey());
        if (expireAt != null) {
            jwtBuilder.setExpiration(expireAt);
        }
        return jwtBuilder.compact();
    }

    public static Claims unSign(String signStr) {
        return Jwts.parser().setSigningKey(SecretKeyUtil.getPublicKey()).parseClaimsJws(signStr).getBody();
    }

    public static void main(String[] args) {
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("password", "aasdsfsdfsdf234324");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        String sign = JWTUtil.sign(payloadMap, calendar.getTime());
        System.out.println(sign);
        Claims claims = JWTUtil.unSign(sign);
        System.out.println(JSON.toJSONString(claims));
    }
}
