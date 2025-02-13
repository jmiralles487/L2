package pe.gob.mtpe.rios.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private final String jwtSecret;
    private final long jwtExpiration;
    private final String jwtIssuer;

    public JwtUtil(String jwtSecret, long jwtExpiration, String jwtIssuer) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
        this.jwtIssuer = jwtIssuer;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}