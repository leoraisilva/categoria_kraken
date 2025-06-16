package br.com.kraken.categoria.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "acesso")
public class AcessoModel implements UserDetails {
    @Id
    private String usuarioId;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
    @Column(name = "rules")
    private Rules rules;

    public AcessoModel(String usuarioId, String usuario, LocalDateTime dataCadastro, Rules rules) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.dataCadastro = dataCadastro;
        this.rules = rules;
    }

    public AcessoModel(){}

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.rules.equals(Rules.ADMIN)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        }
        else  return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

