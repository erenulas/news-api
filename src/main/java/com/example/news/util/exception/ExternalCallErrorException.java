package com.example.news.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ExternalCallErrorException extends HttpStatusCodeException {
    public ExternalCallErrorException(HttpStatus statusCode) {
        super(statusCode);
    }
}
