package com.workjunctionn.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.workjunctionn.Entity.Role;
import com.workjunctionn.Repository.RoleRepository;

import java.util.Arrays;

@Configuration
public class RoleConfig {

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository) {
        return args -> {
            // Check if roles already exist in the database
            if (roleRepository.count() == 0) {
                // Roles are not present, so initialize them
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");

                Role roleJobSeeker = new Role();
                roleJobSeeker.setName("ROLE_JOBSEEKER");

                Role roleRecruiter = new Role();
                roleRecruiter.setName("ROLE_RECRUITER");

                // Save roles to the database
                roleRepository.saveAll(Arrays.asList(roleAdmin, roleJobSeeker, roleRecruiter));
            }
        };
    }
}
