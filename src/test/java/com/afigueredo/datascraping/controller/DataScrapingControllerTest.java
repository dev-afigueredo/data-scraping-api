package com.afigueredo.datascraping.controller;

import com.afigueredo.datascraping.builder.DataScrapingGitHubDtoBuilder;
import com.afigueredo.datascraping.dto.DataScrapingGithubDto;
import com.afigueredo.datascraping.exception.AddressNotFoundException;
import com.afigueredo.datascraping.response.Response;
import com.afigueredo.datascraping.services.impl.DataScrapingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DataScrapingControllerTest {

    private static final String DATA_SCRAPING_API_URL_PATH = "/api/scraping";

    private MockMvc mockMvc;

    @Mock
    private DataScrapingServiceImpl dataScrapingService;

    @InjectMocks
    private DataScrapingController dataScrapingController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dataScrapingController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETIsCalledWithValidUsernameAndRepositoryNameGitHubThenOkStatusIsReturned() throws Exception {
        // given
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("user", "dev-afigueredo");
        requestParams.add("repository", "react-salario");
        Response<List<DataScrapingGithubDto>> response = new Response<>();

        // when
        when(dataScrapingService.findByRepository("dev-afigueredo", "react-salario", response))
                .thenReturn(CompletableFuture.completedFuture(
                        Collections.singletonList(DataScrapingGitHubDtoBuilder
                                .builder()
                                .build()
                                .toDTO())));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(DATA_SCRAPING_API_URL_PATH + "/github")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void whenGETIsCalledWithInvalidUsernameOrRepositoryNameGitHubThenNotFoundStatusIsReturned() throws Exception {
        // given
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("user", "dev-afigueredo");
        requestParams.add("repository", "react-salary");
        Response<List<DataScrapingGithubDto>> response = new Response<>();

        //when
        when(dataScrapingService.findByRepository("dev-afigueredo", "react-salary", response))
                .thenThrow(AddressNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(DATA_SCRAPING_API_URL_PATH + "/github")
                .params(requestParams))
                .andExpect(status().isNotFound());
    }

}