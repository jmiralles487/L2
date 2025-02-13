package pe.gob.mtpe.rios.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

import java.util.Date;
import java.util.Map;

public class JwtUtils2 {

    /**
     * Genera un token JWT con las reclamaciones proporcionadas, una clave secreta y una duración.
     *
     * @param claims      Mapa de reclamaciones que se incluirán en el token.
     * @param secret      Clave secreta utilizada para firmar el token.
     * @param expiration  Duración del token en milisegundos.
     * @return            Token JWT generado como una cadena.
     */
    public static String generateToken(Map<String, Object> claims, String secret, long expiration) {
        return Jwts.builder()
                .setClaims(claims) // Establece las reclamaciones.
                .setIssuedAt(new Date()) // Fecha de creación del token.
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Fecha de expiración.
                .signWith(SignatureAlgorithm.HS256, secret) // Firma el token con HMAC SHA-256 y la clave secreta.
                .compact(); // Genera y retorna el token como una cadena.
    }

    /**
     * Valida un token JWT y verifica si está expirado o si la firma es inválida.
     *
     * @param token  Token JWT a validar.
     * @param secret Clave secreta utilizada para validar la firma del token.
     * @return       true si el token es válido; false de lo contrario.
     */
    public static boolean validateToken(String token, String secret) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());
        } catch (SignatureException e) {
            // Firma inválida
            System.err.println("Firma inválida: " + e.getMessage());
        } catch (Exception e) {
            // Otros errores (por ejemplo, token expirado)
            System.err.println("Error al validar el token: " + e.getMessage());
        }
        return false;
    }

    /**
     * Obtiene las reclamaciones de un token JWT.
     *
     * @param token  Token JWT del cual extraer las reclamaciones.
     * @param secret Clave secreta utilizada para decodificar el token.
     * @return       Las reclamaciones contenidas en el token.
     */
    public static Claims getClaimsFromToken(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}