package uz.pdp.onlinebanking.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    @Value("${jwt.token.expireInMilliSeconds}")
    private Long tokenExpireDate;
    @Value("${jwt.token.refreshExpireInMilliSeconds}")
    private Long refreshTokenExpireDate;
    @Value("${jwt.token.secretKey}")
    private String tokenSecretKey;

    public String generateJwtToken(Integer userId) {
        return
                Jwts
                        .builder()
                        .setSubject(userId.toString())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime()+5000L))
                        .signWith(SignatureAlgorithm.HS512,"OnlineBanking")
                        .compact();
    }

    public String generateRefreshJwtToken(Integer userId) {
        return
                Jwts
                        .builder()
                        .setSubject(userId.toString())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime()+86400000))
                        .signWith(SignatureAlgorithm.HS512,"OnlineBanking")
                        .compact();
    }

    public boolean validateJwtToken(String token, String refreshToken) {
        try {
            Jwts
                    .parser()
                    .setSigningKey("OnlineBanking")
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            try {
                Jwts.parser().setSigningKey("OnlineBanking").parseClaimsJws(refreshToken);
                return true;
            } catch (Exception e1){
                System.out.println("Token is expired!");
            }
        } catch (MalformedJwtException malformedJwtException) {
            System.err.println("Wrong token!");
        } catch (SignatureException s) {
            System.err.println("Wrong key in token!");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            System.err.println("TOken is wrong bro!");
        } catch (IllegalArgumentException ex) {
            System.err.println("Token is empty!");
        }
        return false;
    }

    public Integer getUserIdFromToken(String token, String refreshToken) {
        try {
            String subject = Jwts.parser()
                    .setSigningKey("OnlineBanking")
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return Integer.parseInt(subject);
        } catch (Exception e){
            String subject = Jwts.parser()
                    .setSigningKey("OnlineBanking")
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();
            return Integer.parseInt(subject);
        }
    }

}
