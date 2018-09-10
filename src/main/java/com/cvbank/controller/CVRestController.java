package com.cvbank.controller;

import com.cvbank.model.*;
import com.cvbank.repository.*;
import com.cvbank.response.*;
import com.cvbank.security.CurrentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CVRestController {


    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private com.cvbank.repository.CVRepository CVRepository;

    @Autowired
    private CVactivityRepository cvActivityRepository;

    @Autowired
    private CVEducationRepository cvEducationRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private LanguagesRepository languagesRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    public static <T> List<T> toList(Optional<T> option) {
        if (option.isPresent())
            return Collections.singletonList(option.get());
        else
            return Collections.emptyList();
    }

    private Long getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Profile profile = profileRepository.findByUsername(username);
        return profile.getId();
    }

    @GetMapping("/cv")
    public ResponseEntity getAllCV(@CurrentProfile HttpServletRequest request) {

        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        Response resp;


        if (roles.get(0).toString().equals(Profile.Type.CANDIDATE.name())) {

            System.out.println(profileRepository.findByUsername(request.getUserPrincipal().getName()));
            Long l = profileRepository.findByUsername(request.getUserPrincipal().getName()).getId();

            CVRepository.findCVByProfileId(l);

            resp = new ResponseSuccessList(CVRepository.findCVByProfileId(l));
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.CANDIDATE.name() + " can access [GET] /api/cv");
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/cv")
    @Transactional
    public ResponseEntity<?> createNewCV(@RequestBody CV cv) {

        CV newCv = new CV();
        cv.setId(null);
        cv.setProfileId(getProfile());
        newCv = cv;

        CVRepository.save(newCv);

        List<CVactivity> cvActivity = cv.getCvActivity();
        for (CVactivity cvAct : cvActivity) {
            cvAct.setId(null);
            cvAct.setCv(cv);
            cvActivityRepository.save(cvAct);
        }

        List<Education> education = cv.getEducation();
        for (Education educ : education) {
            educ.setId(null);
            educ.setCv(cv);
            cvEducationRepository.save(educ);
        }
        Template template = cv.getTemplate();
        template.setId(null);
        templateRepository.save(template);
/*
        List<Languages> language = cv.getLanguages();
        for (Languages lang : language) {
            lang.setId(null);
            languagesRepository.save(lang);
        }

        List<Skills> skills = cv.getSkills();
        for (Skills skill : skills) {
            skill.setId(null);
            skillsRepository.save(skill);
        }*/

        Optional<CV> cvResponse = CVRepository.findById(newCv.getId());
        return new ResponseEntity(new ResponseSuccessObject(cvResponse.get()), HttpStatus.CREATED);
    }



    @GetMapping("/cv/{id}")
    public ResponseEntity getCV(@PathVariable Long id) {

        Optional<CV> cv = CVRepository.findById(id);

        if (!cv.isPresent()) {
            ResponseError resp = new ResponseError(1, CV.class.getSimpleName() + " id not found - " + id);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }

        ResponseSuccessObject resp = new ResponseSuccessObject(cv.get());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @PutMapping("/cv/{id}")
    @Transactional
    public ResponseEntity<?> updateCV(@RequestBody CV cv, @PathVariable Long id) {

        Optional<CV> tempCV = CVRepository.findById(id);

        if (!tempCV.isPresent()) {
            ResponseError resp = new ResponseError(2, CV.class.getSimpleName() + " id not found - " + cv.getId());
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }

        cv.setId(id);
        cv.setProfileId(getProfile());
        CVRepository.save(cv);

        List<CVactivity> cvActivity = cv.getCvActivity();
        for (CVactivity cvAct : cvActivity) {
            cvActivityRepository.save(cvAct);
        }

        List<Education> education = cv.getEducation();
        for (Education educ : education) {
            cvEducationRepository.save(educ);
        }
       // Template template = cv.getTemplate();
       // templateRepository.save(template);

        Optional<CV> cvResponse = CVRepository.findById(cv.getId());
        return new ResponseEntity(new ResponseSuccessObject(cvResponse.get()), HttpStatus.CREATED);

    }

    @DeleteMapping("/cv/{id}")
    @Transactional
    public ResponseEntity<?> deleteCV(@PathVariable Long id) {

        Optional<CV> cv = CVRepository.findById(id);
        CV deleteCv = toList(CVRepository.findById(id)).get(0);

        if (cv.isPresent()) {
            List<CVactivity> cvActivity = deleteCv.getCvActivity();
            for (CVactivity cvAct : cvActivity) {
                cvActivityRepository.deleteById(cvAct.getId());
            }

            List<Education> education = deleteCv.getEducation();
            for (Education educ : education) {
                cvEducationRepository.deleteById(educ.getId());
            }

            CVRepository.deleteById(id);

            return new ResponseEntity(new ResponseSuccessEmpty(), HttpStatus.OK);
        }

        ResponseError resp = new ResponseError(3, CV.class.getSimpleName() + " id not found - " + id);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}

