package com.cvbank.security;

import com.cvbank.exception.ResourceNotFoundException;
import com.cvbank.model.Profile;
import com.cvbank.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ProfileRepository profileRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Profile profile = profileRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Profile not found with username or email : " + usernameOrEmail)
        );

        return ProfilePrincipal.create(profile);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Profile", "id", id)
        );

        return ProfilePrincipal.create(profile);
    }
}