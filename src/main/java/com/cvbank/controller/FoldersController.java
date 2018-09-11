package com.cvbank.controller;

import com.cvbank.model.CV;
import com.cvbank.model.CVactivity;
import com.cvbank.model.Folder;
import com.cvbank.model.Profile;
import com.cvbank.repository.CVRepository;
import com.cvbank.repository.FolderRepository;
import com.cvbank.repository.ProfileRepository;
import com.cvbank.response.Response;
import com.cvbank.response.ResponseError;
import com.cvbank.response.ResponseSuccessEmpty;
import com.cvbank.response.ResponseSuccessList;
import com.cvbank.response.ResponseSuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private CVRepository CVRepository;

    @GetMapping("/folders/cv")
    public ResponseEntity getAllFoldersAndCVs(HttpServletRequest request) {

        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Response resp;
        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {

            Long profileId = profileRepository.findByUsername(request.getUserPrincipal().getName()).getId();
            resp = new ResponseSuccessList(folderRepository.findFolderByProfileId(profileId));

        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [GET] /api/folders/cv");
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/folders")
    public ResponseEntity getAllFolders(HttpServletRequest request) {

        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Response resp;
        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {

            Long profileId = profileRepository.findByUsername(request.getUserPrincipal().getName()).getId();

            List<Folder> folderList = folderRepository.findFolderByProfileId(profileId);
            for (Folder folder : folderList) {
                folder.setCv(null);
            }
            resp = new ResponseSuccessList(folderList);
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [GET] /api/folders.");
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/folders/{id}/cv")
    public ResponseEntity getFolder(@PathVariable Long id) {
        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Response resp;
        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {

            Optional<Folder> folder = folderRepository.findById(id);

            if (!folder.isPresent()) {
                resp = new ResponseError(1, Folder.class.getSimpleName() + " id not found - " + id);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            }
            resp = new ResponseSuccessObject(folder.get());
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [GET] /api/folders/{id}/cv.");
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/folders")
    @Transactional
    public ResponseEntity<?> createFolders(@RequestBody Folder folder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List roles = (List) auth.getAuthorities();
        Response resp;
        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {


            String username = auth.getName();
            Profile profile = profileRepository.findByUsername(username);

            Folder newFolder = new Folder();
            folder.setId(null);
            folder.setProfileId(profile.getId());
            newFolder = folder;

            folderRepository.save(newFolder);

            Optional<Folder> folderResponse = folderRepository.findById(newFolder.getId());
            resp = new ResponseSuccessObject(folderResponse.get());
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [POST] /api/folders.");
        }
        return new ResponseEntity(resp, HttpStatus.CREATED);
    }


    @PutMapping("/folders/{id}")
    @Transactional
    public ResponseEntity<?> updateFolder(@RequestBody Folder folder, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List roles = (List) auth.getAuthorities();
        Response resp;
        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {

            String username = auth.getName();
            Profile profile = profileRepository.findByUsername(username);

            Optional<Folder> tempFolder = folderRepository.findById(id);

            if (!tempFolder.isPresent()) {
                resp = new ResponseError(1, Profile.class.getSimpleName() + " id not found - " + folder.getId());
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
            folder.setId(id);
            folder.setProfileId(profile.getId());
            folderRepository.save(folder);

            Optional<Folder> responseFolder = folderRepository.findById(id);
            resp = new ResponseSuccessObject(responseFolder);
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [PUT] /api/folders/{id}.");
        }

        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @DeleteMapping("/folders/{id}")
    @Transactional
    public ResponseEntity<?> deleteFolder(@PathVariable Long id) {
        List roles = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Response resp;

        if (roles.get(0).toString().equals(Profile.Type.COMPANY.name())) {

            Optional<Folder> folder = folderRepository.findById(id);
            if (folder.isPresent()) {
                folderRepository.deleteById(id);

                return new ResponseEntity(new ResponseSuccessEmpty(), HttpStatus.OK);
            }
            resp = new ResponseError(1, Profile.class.getSimpleName() + " id not found - " + id);
        } else {
            resp = new ResponseError(4, "ONLY" + Profile.Type.COMPANY.name() + " can access [DELETE] /api/folders/{id}.");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


}
