package com.afigueredo.datascraping.controller;

import com.afigueredo.datascraping.domain.File;
import com.afigueredo.datascraping.dto.DataScrapingGithubDto;
import com.afigueredo.datascraping.exception.AddressNotFoundException;
import com.afigueredo.datascraping.response.Response;
import com.afigueredo.datascraping.services.DataScrapingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/scraping")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataScrapingController implements DataScrapingDocs {

    private final DataScrapingService dataScrapingService;

    @GetMapping(value = "/github")
    public ResponseEntity<Response<List<DataScrapingGithubDto>>> analyzeFileGithub(@RequestParam(name = "user") String user,
                                                                                   @RequestParam(name = "repository") String repository) throws IOException {

        Response<List<DataScrapingGithubDto>> response = new Response<>();

        CompletableFuture<List<File>> byRepository = dataScrapingService.findByRepository(user, repository, response);

        if(response.hasErrors())
            throw new AddressNotFoundException(user, repository);

        List<DataScrapingGithubDto> listDto = new DataScrapingGithubDto().toList(byRepository.getNow(null));

        response.setData(listDto);
        return ResponseEntity.ok().body(response);
    }

}
