package com.afigueredo.datascraping.services;

import com.afigueredo.datascraping.domain.File;
import com.afigueredo.datascraping.dto.DataScrapingGithubDto;
import com.afigueredo.datascraping.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DataScrapingService {

    /**
     * This method is used to find a repository in Github
     *
     * @return List with details of Files in a determinate repository
     */
    CompletableFuture<List<File>> findByRepository(String user, String repository, Response<List<DataScrapingGithubDto>> response) throws IOException;

    /**
     * This method is used to get details from a repository, downloading by a zip file
     *
     * @return List with details of Files in a determinate repository
     */
    CompletableFuture<List<File>> searchDetails(String user, String repository, Response<List<DataScrapingGithubDto>> response) throws IOException;
}
