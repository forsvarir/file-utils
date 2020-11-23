package com.forsvarir.fileutils.web;

import com.forsvarir.fileutils.model.FileDetail;
import com.forsvarir.fileutils.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@AutoConfigureMockMvc
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private FileService fileService;

    @BeforeEach
    void beforeEach() {
        fileService = mock(FileService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FileController(fileService))
                .build();
    }

    @Test
    @DisplayName("GET /file-utils/files/1 - Found")
    void getNormalFileIsFound() throws Exception {
        var expectedFileDetails = new FileDetail("/oh/", "SomeFile", 1);
        when(fileService.findById(any())).thenReturn(expectedFileDetails);

        // Execute the GET request
        var response = mockMvc.perform(get("/file-utils/files/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("SomeFile")));
    }
}