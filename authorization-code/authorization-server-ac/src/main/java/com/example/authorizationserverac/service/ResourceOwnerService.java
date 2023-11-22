package com.example.authorizationserverac.service;


import com.example.authorizationserverac.dto.ResourceOwnerDTO;
import com.example.authorizationserverac.dto.ResourceOwnerTypeDTO;
import com.example.authorizationserverac.entity.ResourceOwnerEntity;
import com.example.authorizationserverac.repository.ResourceOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceOwnerService implements UserDetailsService {

    private final ResourceOwnerRepository resourceOwnerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return resourceOwnerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public void createUser(ResourceOwnerDTO resourceOwnerDTO) {
        if (resourceOwnerRepository.existsByUsername(resourceOwnerDTO.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        var ro = ResourceOwnerEntity.valueOf(resourceOwnerDTO);
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        resourceOwnerRepository.save(ro);
    }

    public void changeUserType(ResourceOwnerTypeDTO roTypeDTO) {
        var user = resourceOwnerRepository.findByUsername(roTypeDTO.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (roTypeDTO.resourceOwnerType().equals(user.getResourceOwnerType())) {
            return;
        }

        user.setResourceOwnerType(roTypeDTO.resourceOwnerType());
        resourceOwnerRepository.save(user);

    }
}
