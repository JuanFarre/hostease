package com.Hostease.Hostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;
    @Column(name = "fecha_creacion")
    private Instant fecha_creacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fecha_modificacion;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_tipo_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tipo_usuario")
    )
    private Set<TipoUsuario> tipoUsuarios = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (tipoUsuarios == null || tipoUsuarios.isEmpty()) {
            return new ArrayList<>(); // Retorna una lista vacía si no hay roles
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Recorre la lista de tipoUsuarios y agrega cada rol con el prefijo "ROLE"
        for (TipoUsuario tipoUsuario : tipoUsuarios) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.getNombre()));
        }
        return authorities;
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
