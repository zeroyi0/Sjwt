package com.shiro.sjwtm.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.tomcat.util.security.MD5Encoder;

import java.util.Date;

public class JWTUtils {

    public static final Long EXPIRE_TIME = 3600L;

    public static final byte[] SECRET = "xiaoJuan".getBytes();

    public static String generateToken(String userName) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                    .withClaim("userName", userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
    }

    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String userName;
            userName = jwt.getClaim("userName")
                    .asString();
            return userName;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean verify(String token, String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", userName)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
