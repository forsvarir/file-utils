package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.services.model.BatchDetail;
import com.forsvarir.file.utils.services.services.BatchService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.forsvarir.file.utils.services.web.FileControllerTest.asJsonString;

class BatchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private BatchService batchService;

    @BeforeEach
    void beforeEach() {
        batchService = Mockito.mock(BatchService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BatchController(batchService))
                .build();
    }

    @Test
    @DisplayName("POST /file-utils/batch - Success")
    void postBatchCreatedWithIdFromService() throws Exception {
        var expectedBatchDetails = new BatchDetail(55L);
        Mockito.when(batchService.create()).thenReturn(expectedBatchDetails);

        mockMvc.perform(MockMvcRequestBuilders.post("/file-utils/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString("")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // Validate the headers
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "/file-utils/batch/55"))
                // Validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(55)));
    }

}