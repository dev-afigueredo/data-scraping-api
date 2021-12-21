package com.afigueredo.datascraping.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T data;
    private List<String> errors = new ArrayList<>();

    public void addErro(String message) {
        this.data = null;
        this.errors.add(message);
    }

    public boolean hasErrors() {
        return this.errors != null && !this.errors.isEmpty();
    }

}
