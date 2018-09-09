package com.cvbank.controller;

import com.cvbank.model.Languages;
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
public class LanguagesController {

    @Autowired
    private com.cvbank.repository.LanguagesRepository LanguagesRepository;

    @GetMapping("/languages")
    public ResponseEntity getAllCV(HttpServletRequest request) {

        List<Languages> language_list = LanguagesRepository.findAll();

        return new ResponseEntity<>(new ResponseSuccessList(language_list), HttpStatus.OK);
    }
}

