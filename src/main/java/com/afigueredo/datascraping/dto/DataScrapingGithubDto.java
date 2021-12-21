package com.afigueredo.datascraping.dto;

import com.afigueredo.datascraping.domain.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataScrapingGithubDto {

    private String extension;
    private Long countFoundExtension;
    private Long lineFoundExtension;
    private Long sizeTotalExtension;

    public List<DataScrapingGithubDto> toList(List<File> listObj) {
        List<DataScrapingGithubDto> listDto = new ArrayList<>();

        Map<String, Long> resultNumberLines = listObj.stream()
                .collect(Collectors.groupingBy(File::getExtensionFile, Collectors.summingLong(File::getNumberLinesFile)));
        Map<String, Long> resultCount = listObj.stream()
                .collect(Collectors.groupingBy(File::getExtensionFile, Collectors.summingLong(File::getCountFile)));
        Map<String, Long> resultSize = listObj.stream()
                .collect(Collectors.groupingBy(File::getExtensionFile, Collectors.summingLong(File::getSizeFile)));

        resultNumberLines.keySet().forEach(extensionFile -> {
            DataScrapingGithubDto dataScrapingGithubDto = new DataScrapingGithubDto();
            dataScrapingGithubDto.setLineFoundExtension(resultNumberLines.get(extensionFile));
            dataScrapingGithubDto.setCountFoundExtension(resultCount.get(extensionFile));
            dataScrapingGithubDto.setSizeTotalExtension(resultSize.get(extensionFile));
            dataScrapingGithubDto.setExtension(extensionFile);
            listDto.add(dataScrapingGithubDto);
        });

        return listDto;
    }

}
