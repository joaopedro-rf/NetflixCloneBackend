package com.joao.netflixClone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(
        name = "netflix_user"
)
@Table(
        name = "netflix_user"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class NetflixUser implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private @NotEmpty(
            message = "Username must not be empty"
    ) String username;
    private @NotNull(
            message = "Email must not be empty"
    ) @Email String email;
    private @NotEmpty(
            message = "Password must not be empty"
    ) String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "created_timestamp",
            updatable = false
    )
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "updated_timestamp",
            nullable = true
    )
    private Date updatedAt;

    public NetflixUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
