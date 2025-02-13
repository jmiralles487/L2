package pe.gob.mtpe.rios.seguridad;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";
    private final String secret = "6LcgKoQqAAAAAPUaYvSTJY6RkEZUWevkVMDelSCmgKoQqAAAAAPUaYvSTJY6"; // Usa una clave secreta segura, tal vez desde propiedades

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        // Verifica que el token JWT esté presente y sea un token válido de tipo Bearer
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String token = authHeader.substring(BEARER_PREFIX.length());

            try {
                Claims claims = JwtUtils2.getClaimsFromToken(token, secret);

                // Extraer el nombre de usuario o sujeto desde el token
                String username = claims.getSubject();
                if (username != null) {
                    // Cargar los detalles del usuario desde el token (si es necesario)
                    UserDetails userDetails = User.builder()
                            .username(username)
                            .password("") // No se necesita contraseña aquí
                            .authorities("ROLE_ADMIN") // Asigna roles según sea necesario
                            .build();

                    // Crear un objeto de autenticación y establecerlo en el contexto de seguridad
                    var authentication = new JwtAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Establecer el contexto de seguridad para esta solicitud
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception ex) {
                // Manejo de errores: token inválido o expirado
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado");
                return; // Detener el procesamiento de la solicitud si el token es inválido
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
