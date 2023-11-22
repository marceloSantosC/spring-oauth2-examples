package com.example.authorizationserverac.entity;


import com.example.authorizationserverac.dto.ResourceOwnerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`USER`")
public class ResourceOwnerEntity implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @NotNull
    @Enumerated(EnumType.STRING)
    private ResourceOwnerType resourceOwnerType;

    private String email;

    @CreatedDate
    private OffsetDateTime createdAt;

    private boolean active;

    public static ResourceOwnerEntity valueOf(ResourceOwnerDTO resourceOwnerDTO) {
        return ResourceOwnerEntity.builder()
                .username(resourceOwnerDTO.username())
                .password(resourceOwnerDTO.password())
                .resourceOwnerType(ResourceOwnerType.CUSTOMER)
                .email(resourceOwnerDTO.email())
                .active(true)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(resourceOwnerType.name()));
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
        return active;
    }
}
