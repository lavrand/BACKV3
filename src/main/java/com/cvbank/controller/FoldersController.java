package com.cvbank.controller;

import com.cvbank.model.Folder;
import com.cvbank.model.Profile;
import com.cvbank.repository.FolderRepository;
import com.cvbank.repository.ProfileRepository;
import com.cvbank.response.Response;
import com.cvbank.response.ResponseError;
import com.cvbank.response.ResponseSuccessList;
import com.cvbank.response.ResponseSuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FoldersController {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/folders/cv")
    public ResponseEntity getAllFoldersAndCVs(HttpServletRequest request) {

        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Long profileId = profileRepository.findByUsername(request.getUserPrincipal().getName()).getId();

        Response resp = new ResponseSuccessList(folderRepository.findFolderByProfileId(profileId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/folders")
    public ResponseEntity getAllFolders(HttpServletRequest request) {

        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Long profileId = profileRepository.findByUsername(request.getUserPrincipal().getName()).getId();

        List<Folder> folderList = folderRepository.findFolderByProfileId(profileId);
        for (Folder folder : folderList) {
            folder.setCv(null);
        }

        return new ResponseEntity<>(new ResponseSuccessList(folderList), HttpStatus.OK);
    }

    @GetMapping("/folders/{id}")
    public ResponseEntity getFolder(@PathVariable Long id) {

        Optional<Folder> folder = folderRepository.findById(id);

        if (!folder.isPresent()) {
            ResponseError resp = new ResponseError(1, Folder.class.getSimpleName() + " id not found - " + id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        ResponseSuccessObject resp = new ResponseSuccessObject(folder.get());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/folders")
    @Transactional
    public ResponseEntity<?> createFolders(@RequestBody Folder folder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Profile profile = profileRepository.findByUsername(username);

        Folder newFolder = new Folder();
        folder.setId(null);
       // folder.setProfile(profile);
        folder.setProfileId(profile.getId());
        newFolder = folder;

        folderRepository.save(newFolder);

       // List<FolderProfile> folderProfile = folder.getProfile();


        Optional<Folder> folderResponse = folderRepository.findById(newFolder.getId());
        return new ResponseEntity(new ResponseSuccessObject(folderResponse.get()), HttpStatus.CREATED);
    }
}
