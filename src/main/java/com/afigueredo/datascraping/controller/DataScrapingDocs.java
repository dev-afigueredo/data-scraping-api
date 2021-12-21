package com.afigueredo.datascraping.controller;

import com.afigueredo.datascraping.dto.DataScrapingGithubDto;
import com.afigueredo.datascraping.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Tag(name = "Scrap File", description = "Data Scraping File")
public interface DataScrapingDocs {

    @Operation(description  = "Return the file count and the total number of lines, grouped by file extension, of a given public Github repository")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "File found"),
            @ApiResponse(responseCode  = "400", description  = "Missing required fields or wrong address.")
    })
    ResponseEntity<Response<List<DataScrapingGithubDto>>> analyzeFileGithub(@RequestParam(name = "user") String user,
                                                                            @RequestParam(name = "repository") String repository) throws IOException;

}
