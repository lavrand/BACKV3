package com.cvbank.controller;

import com.cvbank.model.CV;
import com.cvbank.response.ResponseSuccessList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CVSearchController {

    @Autowired
    private com.cvbank.repository.CVRepository CVRepository;

    @PersistenceContext
    public EntityManager em;


    @RequestMapping(value = "/cvsearch", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> searchCv(@RequestBody Object object) {
        int sallary = 10000;
        String position = "";

        Query result = em.createNativeQuery("SELECT * FROM cv");
        List<CV> cv_list_result = result.getResultList();

        ResponseSuccessList response = new ResponseSuccessList(cv_list_result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}