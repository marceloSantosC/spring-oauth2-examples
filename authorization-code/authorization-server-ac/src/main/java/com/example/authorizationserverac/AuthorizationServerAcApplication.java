package com.example.authorizationserverac;

import com.example.authorizationserverac.entity.ResourceOwnerEntity;
import com.example.authorizationserverac.entity.ResourceOwnerType;
import com.example.authorizationserverac.repository.ResourceOwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationServerAcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerAcApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertDefaultUserOnStartup(ResourceOwnerRepository resourceOwnerRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            ResourceOwnerEntity user = ResourceOwnerEntity.builder()
                    .resourceOwnerType(ResourceOwnerType.CUSTOMER)
                    .password(passwordEncoder.encode("admin"))
                    .username("admin")
                    .email("admin@email.com")
                    .active(true)
                    .build();
            resourceOwnerRepository.save(user);
        };
    }

}
