package com.afigueredo.datascraping.services.impl;

import com.afigueredo.datascraping.domain.File;
import com.afigueredo.datascraping.dto.DataScrapingGithubDto;
import com.afigueredo.datascraping.response.Response;
import com.afigueredo.datascraping.services.DataScrapingService;
import com.afigueredo.datascraping.utils.ReadFileUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataScrapingServiceImpl implements DataScrapingService {


    public CompletableFuture<List<File>> findByRepository(String user, String repository, Response<List<DataScrapingGithubDto>> response) throws IOException {
        return searchDetails(user, repository, response);
    }

    @Async
    public CompletableFuture<List<File>> searchDetails(String user, String repository, Response<List<DataScrapingGithubDto>> response) throws IOException {
        List<File> list = new ArrayList<>();
        URL url = new URL("https://github.com/" + user + "/" + repository + "/archive/master.zip"); // or main.zip

        try {
            list = ReadFileUtil.readZipStream(url);
        } catch (IOException e) {
            response.addErro(e.getMessage());
        }

        return CompletableFuture.completedFuture(list);
    }

}
