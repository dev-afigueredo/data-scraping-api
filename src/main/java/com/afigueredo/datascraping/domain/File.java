package com.afigueredo.datascraping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    private Long countFile;
    private Long numberLinesFile;
    private Long sizeFile;
    private String extensionFile;

}
