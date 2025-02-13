package pe.gob.mtpe.rios.seguridad;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {

    private final UserDetails principal;
    private Object credentials;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated;
    private Object details;  // Para almacenar los detalles de autenticación

    public JwtAuthenticationToken(UserDetails principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        this.principal = principal;
        this.credentials = credentials;
        this.authorities = authorities;
        this.authenticated = true; // Este token es válido, por lo que está autenticado
    }

    @Override
    public String getName() {
        return principal.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public Object getDetails() {
        return details;  // Retorna los detalles de la autenticación
    }

    public void setDetails(Object details) {
        this.details = details;  // Establece los detalles de la autenticación
    }
}