package com.cvbank.controller;

import com.cvbank.model.Profile;
import com.cvbank.repository.ProfileRepository;
import com.cvbank.response.*;
import com.cvbank.security.JwtTokenProvider;
import com.cvbank.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    NotificationService notificationService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<Profile> profile = profileRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseSuccessObject(new LoginResponse(profile, jwt)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Profile profile) {
//        if (profileRepository.existsByUsername(profile.getUsername())) {
//            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        if (profileRepository.existsByEmail(profile.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating profile

        profile.setPassword(passwordEncoder.encode(profile.getPassword()));

        Profile result = profileRepository.save(profile);
        try {
            notificationService.sendNotification(profile);
        } catch (MailException e) {
            System.out.println("Error sending mail " + e.getMessage());
        }
        SignUpResponse dataResponse = new SignUpResponse(result.getId().toString(), result.getUsername(), result.getUsertype());
        ResponseSuccessObject resp = new ResponseSuccessObject(dataResponse);

        return new ResponseEntity<>(resp, HttpStatus.CREATED);

    }
}
