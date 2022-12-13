package com.example.news.service.search;

import com.example.news.model.bo.NewsBo;
import com.example.news.util.exception.ExternalCallErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsSearchServiceImpl implements NewsSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSearchServiceImpl.class);
    private static final String QUERY_PARAM_SEPARATOR = "&";
    private static final String GNEWS_URL = "https://gnews.io/api/v4/search";

    @Value("${gnews.api.token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Cacheable("newsSearchResults")
    public NewsBo search(String query, boolean searchByTitle, int size) {
        String url = createRequestURL(query, searchByTitle, size);
        return searchArticles(url);
    }

    private String createRequestURL(String query, boolean searchByTitle, int size) {
        StringBuilder url =
                new StringBuilder(GNEWS_URL).append("?token=").append(token)
                        .append(QUERY_PARAM_SEPARATOR).append("q=").append(query)
                        .append(QUERY_PARAM_SEPARATOR).append("max=").append(size);
        if (searchByTitle) {
            url.append(QUERY_PARAM_SEPARATOR).append("in=").append("title");
        }
        return url.toString();
    }
    private NewsBo searchArticles(String uri) {
        try {
            ResponseEntity<NewsBo> newsBo = restTemplate.getForEntity(uri, NewsBo.class);
            LOGGER.info("Articles are retrieved successfully.");
            return newsBo.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error("Client error occurred when calling external api: ", e);
            throw new ExternalCallErrorException(e.getStatusCode());
        }
    }
}
