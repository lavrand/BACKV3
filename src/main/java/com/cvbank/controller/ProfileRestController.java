package com.cvbank.controller;

import com.cvbank.model.Profile;
import com.cvbank.repository.ProfileRepository;
import com.cvbank.response.ResponseError;
import com.cvbank.response.ResponseSuccessEmpty;
import com.cvbank.response.ResponseSuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfileRestController {


    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profiles/{id}")
    public ResponseEntity getProfile(@PathVariable Long id) {

        Optional<Profile> profile = profileRepository.findById(id);

        if (!profile.isPresent()) {
            ResponseError response = new ResponseError(1, Profile.class.getSimpleName() + " id not found - " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseSuccessObject(profile), HttpStatus.OK);
    }

    @PutMapping("/profiles/{id}")
    @Transactional
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile, @PathVariable Long id) {

        Optional<Profile> tempProfile = profileRepository.findById(id);

        if (!tempProfile.isPresent()) {
            ResponseError resp = new ResponseError(2, Profile.class.getSimpleName() + " id not found - " + profile.getId());
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        profile.setId(id);
        profileRepository.save(profile);


        Optional<Profile> responseProfile = profileRepository.findById(id);
        return new ResponseEntity(new ResponseSuccessObject(responseProfile), HttpStatus.OK);
    }

    @DeleteMapping("/profiles/{id}")
    @Transactional
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {

        Optional<Profile> profile = profileRepository.findById(id);

        if (profile.isPresent()) {
            profileRepository.deleteById(id);

            ResponseSuccessEmpty resp = new ResponseSuccessEmpty();
            return new ResponseEntity(resp, HttpStatus.OK);
        }

        ResponseError resp = new ResponseError(3, Profile.class.getSimpleName() + " id not found - " + id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}

