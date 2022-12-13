package com.example.news.service.search;

import com.example.news.model.bo.NewsBo;

public interface NewsSearchService {

    NewsBo search(String query, boolean searchByTitle, int size);

}
