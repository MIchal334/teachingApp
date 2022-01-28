package com.teatching_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String SECRET_KEY = "aaa";
    String encodedString = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> climeResolver) {
        final Claims claims = extractAllClaims(token);
        return climeResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(encodedString))
                .parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return ceateToken(claims, userDetails.getUsername());
    }

    private String ceateToken(Map<String, Object> claims, String username) {

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
                .signWith(SignatureAlgorithm.HS256,encodedString).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpird(token));
    }

    private Boolean isTokenExpird(String token) {
        return extractExpiration(token).before(new Date());
    }
}
