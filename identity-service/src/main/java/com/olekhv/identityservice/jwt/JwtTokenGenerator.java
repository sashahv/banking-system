package com.olekhv.identityservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    private static final String SECRET_KEY = "51655368566D597133743677397A24432646294A404E635266556A576E5A7234";

    private static final Long EXPIRE_TIME = 86400000L;

    public String generateJwtToken(Authentication authentication){
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Long findUserIdByToken(String token){
        Jws<Claims> claimsJwt = parseToken(token);

        String userIdStr = claimsJwt
                .getBody()
                .getSubject();

        return Long.parseLong(userIdStr);
    }

    private Jws<Claims> parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token);
    }

    public boolean validateToken(String token){
        boolean isValid;

        try{
            Jws<Claims> claimsJws = parseToken(token);

            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e){
            isValid = false;
        }

        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {
        Date expirationDate = claimsJws.getBody().getExpiration();

        return expirationDate.before(new Date());
    }

    private Key getSignInKey() {
        byte[] keys = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }

}
