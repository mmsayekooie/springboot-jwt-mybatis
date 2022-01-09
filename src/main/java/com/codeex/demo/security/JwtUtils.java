package com.codeex.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    //Authentication is equivalent to saving the private key on the server
    private  static  final String  secret="@@###$##$!@@%&";



    public static  String getToken(Map<String,String> map)
    {
        Calendar instance=Calendar.getInstance();
        //By default, it expires in seven days
        instance.add(Calendar.DATE,7);
        //Create JWT
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //Specify token expiration time
        builder.withExpiresAt(instance.getTime());

        String token=builder.sign(Algorithm.HMAC256(secret));
        return token;
    }


    public  static DecodedJWT verify(String token) {
        return  JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

}