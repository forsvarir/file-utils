package com.forsvarir.file.utils.services.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forsvarir.file.utils.common.api.data.CreateFileRequest;
import com.forsvarir.file.utils.common.api.data.FileDetail;
import com.forsvarir.file.utils.services.services.BatchFileProcessing;
import com.forsvarir.file.utils.services.services.FileDetailService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private FileDetailService fileDetailService;
    private BatchFileProcessing batchFileProcessingService;

    @BeforeEach
    void beforeEach() {
        fileDetailService = mock(FileDetailService.class);
        batchFileProcessingService = mock(BatchFileProcessing.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FileController(fileDetailService, batchFileProcessingService))
                .build();
    }

    @Test
    @DisplayName("GET /file-utils/files/1 - Found")
    void getNormalFileIsFound() throws Exception {
        var expectedFileDetails = new FileDetail("SomeFile", "/oh/", 5000L, 1);
        when(fileDetailService.findById(any())).thenReturn(expectedFileDetails);

        mockMvc.perform(MockMvcRequestBuilders.get("/file-utils/files/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("SomeFile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size", Matchers.is(5000)));
    }

    @Test
    @DisplayName("POST /file-utils/files - Success")
    void postNormalFileIsAdded() throws Exception {
        var expectedFileDetails = new FileDetail("SavedFile", "/savedPath/", 999L, 55);
        when(batchFileProcessingService.addFileToBatch(any(), anyLong())).thenReturn(expectedFileDetails);

        var postedFileDetails = new FileDetail("/postedPath/", "PostedFile", 5000L, 0);

        mockMvc.perform(MockMvcRequestBuilders.post("/file-utils/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postedFileDetails)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // Validate the headers
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "/file-utils/files/55"))
                // Validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(55)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("SavedFile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size", Matchers.is(999)));
    }

    @Test
    @DisplayName("POST /file-utils/files - Valid")
    void postNormalFileIsSavedCorrectly() throws Exception {
        var postedFileDetails = new CreateFileRequest(99L,
                new FileDetail("PostedFile", "/postedPath/", 5000L, 0));
        ArgumentCaptor<FileDetail> savedFileCaptor = ArgumentCaptor.forClass(FileDetail.class);
        when(batchFileProcessingService.addFileToBatch(any(), anyLong())).thenReturn(postedFileDetails.getFileDetail());

        mockMvc.perform(MockMvcRequestBuilders.post("/file-utils/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postedFileDetails)));

        verify(batchFileProcessingService).addFileToBatch(savedFileCaptor.capture(), eq(99L));
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
