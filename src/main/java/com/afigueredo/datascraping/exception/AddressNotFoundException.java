package com.afigueredo.datascraping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends IOException {

    public AddressNotFoundException(String user, String repository) {
        super(String.format("User '%s' or repository '%s' not found or not public.", user, repository));
    }

}
