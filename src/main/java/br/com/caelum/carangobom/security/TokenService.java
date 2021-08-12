package br.com.caelum.carangobom.security;

import br.com.caelum.carangobom.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${curso.jwt.expiration}")
    private String experation;

    @Value("${curso.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        User login = (User) authentication.getPrincipal();
        Date now = new Date();
        Date dateEx = new Date(now.getTime() + Long.parseLong(experation));


        return Jwts.builder()
                .setIssuer("API Carango Bom")
                .setSubject(login.getId().toString())
                .setIssuedAt(now)
                .setExpiration(dateEx)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
