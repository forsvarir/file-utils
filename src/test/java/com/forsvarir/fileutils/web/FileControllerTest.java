package com.forsvarir.fileutils.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forsvarir.fileutils.model.FileDetail;
import com.forsvarir.fileutils.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
        var expectedFileDetails = new FileDetail("/oh/", "SomeFile", 5000L, 1);
        when(fileService.findById(any())).thenReturn(expectedFileDetails);

        var response = mockMvc.perform(get("/file-utils/files/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("SomeFile")))
                .andExpect(jsonPath("$.size", is(5000)));
    }

    @Test
    @DisplayName("POST /file-utils/files - Success")
    void postNormalFileIsAdded() throws Exception {
        var expectedFileDetails = new FileDetail("/savedPath/", "SavedFile", 999L, 55);
        when(fileService.save(any())).thenReturn(expectedFileDetails);

        var postedFileDetails = new FileDetail("/postedPath/", "PostedFile", 5000L, 0);

        mockMvc.perform(post("/file-utils/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postedFileDetails)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // Validate the headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/file-utils/files/55"))
                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(55)))
                .andExpect(jsonPath("$.name", is("SavedFile")))
                .andExpect(jsonPath("$.size", is(999)));
    }

    @Test
    @DisplayName("POST /file-utils/files - Valid")
    void postNormalFileIsSavedCorrectly() throws Exception {
        var postedFileDetails = new FileDetail("/postedPath/", "PostedFile", 5000L, 0);
        ArgumentCaptor<FileDetail> savedFileCaptor = ArgumentCaptor.forClass(FileDetail.class);
        when(fileService.save(savedFileCaptor.capture())).thenReturn(postedFileDetails);

        mockMvc.perform(post("/file-utils/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postedFileDetails)));

        assertThat(savedFileCaptor.getAllValues()).hasSize(1);
        assertThat(savedFileCaptor.getValue().getId()).isEqualTo(0);
        assertThat(savedFileCaptor.getValue().getName()).isEqualTo("PostedFile");
        assertThat(savedFileCaptor.getValue().getPath()).isEqualTo("/postedPath/");
        assertThat(savedFileCaptor.getValue().getSize()).isEqualTo(5000L);
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
