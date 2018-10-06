package com.cvbank.controller;

import com.cvbank.model.Skill;
import com.cvbank.response.ResponseSuccessList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillsController {

    @Autowired
    private com.cvbank.repository.SkillRepository SkillRepository;

    @GetMapping("/skills")
    public ResponseEntity getAllSkills(HttpServletRequest request) {

        List<Skill> skill_list = SkillRepository.findAll();

        return new ResponseEntity<>(new ResponseSuccessList(skill_list), HttpStatus.OK);
    }
}

