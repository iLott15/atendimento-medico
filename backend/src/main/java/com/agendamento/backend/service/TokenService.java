package com.agendamento.backend.service;

import com.agendamento.backend.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET = "MinhaChaveSuperSecretaParaJWTComMaisDe32Caracteres!";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1h

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // TokenService.java
    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("role", usuario.getRole()) // ADICIONA a role aqui, tipo "ADMIN"
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getRoleFromToken(String token) {
        try {
            var claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("role", String.class); // pega a role "ADMIN"
        } catch (JwtException e) {
            return null;
        }
    }

    public String validarToken(String token) {
        try {
            var claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}