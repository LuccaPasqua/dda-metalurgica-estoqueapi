package br.org.sesisenai.estoqueapi.config;
import br.org.sesisenai.estoqueapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.hour:24}")
    private Long expirationHours;

    // Converte a string da chave em um objeto criptográfico seguro (HMAC SHA-256)
    private SecretKey getSignKey(){
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // GERAÇÃO DO TOKEN
    public String generateToken(User user){
        Instant now = Instant.now();
        Instant expiration = now.plus(expirationHours, ChronoUnit.HOURS);

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole().name())
                .issuedAt(Date.from(expiration))
                .signWith(getSignKey())
                .compact();
    }



    // EXTRAÇÃO DE DADOS(Quando o usuário faz uma requisição)
    public String extractEmail(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // VALIDAção TOKEN
    public boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token);
            return true;  //Se não lançou exceção, o token foi assinado por nós e não expirou
        } catch (Exception e) {
            return false;
        }
    }


}
