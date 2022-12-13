package com.example.news.controller;

import com.example.news.model.api.NewsRES;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
public interface NewsApi {

    @GetMapping("/news")
    ResponseEntity<NewsRES> searchNews(@RequestParam(value = "query", required = false) String query,
                                       @RequestParam(value = "searchByTitle", defaultValue = "false") boolean searchByTitle,
                                       @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(10) int size);

}
