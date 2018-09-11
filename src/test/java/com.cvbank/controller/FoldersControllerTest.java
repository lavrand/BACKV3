package com.cvbank.controller;

import com.cvbank.model.Folder;
import com.cvbank.repository.FolderRepository;
import com.cvbank.repository.ProfileRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FoldersController.class, secure = false)
class FoldersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FolderRepository folderRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Test
    void getAllFoldersAndCVs() {

    }

    @Test
    void getAllFolders() throws Exception {

/*
        Folder mockFolder = new Folder();
        mockFolder.setNameFolder("Test");
        mockFolder.setProfileId(7L);
        List<Folder> mockFolders = new ArrayList<Folder>();
        mockFolders.add(mockFolder);
*/

    }

    @Test
    void getFolder() {
    }

    @Test
    void createFolders() {
    }

    @Test
    void updateFolder() {
    }

    @Test
    void deleteFolder() {
    }
}