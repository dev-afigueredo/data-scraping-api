package com.afigueredo.datascraping.builder;

import com.afigueredo.datascraping.domain.File;
import lombok.Builder;

@Builder
public class DataScrapingGitHubDtoBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String extension = "java";

    @Builder.Default
    private final Long numberLines = 70L;

    @Builder.Default
    private final Long count = 2L;

    @Builder.Default
    private final Long size = 1L;

    public File toDTO() {
        return new File(id, numberLines, count, size, extension);
    }
}
