package com.example.authorizationService.util;

import com.example.authorizationService.exceptions.InvalidTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${app.secretKey}")
    private String secretKey;

    @Value("${app.jwtValidityMinutes}")
    private long jwtValidityMinutes;

    @Value("${jwtUtil.expiredMessage}")
    private String EXPIRED_MESSAGE;

    @Value("${jwtUtil.malformedMessage}")
    private String MALFORMED_MESSAGE;

    @Value("${jwtUtil.nullOrEmptyMessage}")
    private String TOKEN_NULL_OR_EMPTY_MESSAGE;

    @Value("${jwtUtil.signatureMessage}")
    private String SIGNATURE_MESSAGE;

    @Value("${jwtUtil.unsupportedMessage}")
    private String UNSUPPORTED_MESSAGE;

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String subject) {
        return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(jwtValidityMinutes)))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpiredOrInvalidFormat(String token) {
        try {
            getClaims(token);
        } catch (ExpiredJwtException e) { // caught first cause it has higher precedence
            throw new InvalidTokenException(EXPIRED_MESSAGE);
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException(MALFORMED_MESSAGE);
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException(TOKEN_NULL_OR_EMPTY_MESSAGE);
        } catch (SignatureException e) {
            throw new InvalidTokenException(SIGNATURE_MESSAGE);
        }
        return false;
    }
}
