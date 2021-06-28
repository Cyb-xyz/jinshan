package com.jinshan.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  token令牌工具类
 * @Auther: 小宇宙
 * @Date: 2021-05-19 11:06
 */
public class TokenUtils {
    //token秘钥
    private static final String TOKEN_SECRET = "JSMiYaoUUIDJinShanChengYun2021XYZ";
    /**
     *  获取token
     * @param appId     应用id
     * @param secret    登录密钥
     * @return
     */
    public static String getToken (String appId, String secret){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("appId", appId);
        claims.put("secret", secret);
        //生成token
        String token = Jwts.builder()
                .setClaims(claims)
                .setId(appId)
                .setSubject(secret)
                .setExpiration(new Date(System.currentTimeMillis() + 30*60000))  //过期时间,30分钟
                .setIssuedAt(new Date(System.currentTimeMillis()))  //当前时间
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)   //头部信息 第一个参数为加密方式为哈希512  第二个参数为加的盐TOKEN_SECRET为密钥
                .compact();

//        Claims claims1 = Jwts.parser()
//                .setSigningKey(TOKEN_SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//        Date d1 = claims1.getIssuedAt();
//        Date d2 = claims1.getExpiration();
//        System.out.println("登录用户的id：" + claims1.getId());
//        System.out.println("登录用户的名称：" + claims1.getSubject());
//        System.out.println("令牌签发时间：" + sdf.format(d1));
//        System.out.println("令牌过期时间：" + sdf.format(d2));

        return token;
    }

    /**
     *  验证token令牌
     * @param token 需要校验的令牌
     * @return  返回是否正确
     */
    public static boolean verify(String token){
        boolean result = true;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
