package com.example.news.util.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.management.timer.Timer;

@EnableScheduling
@Configuration
public class NewsCacheConfiguration {
    public static final String NEWS_SEARCH_RESULTS_CACHE = "newsSearchResults";
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsCacheConfiguration.class);

    @Scheduled(fixedRate = Timer.ONE_MINUTE)
    @CacheEvict(value = {NEWS_SEARCH_RESULTS_CACHE}, allEntries = true)
    public void clearEvents() {
        LOGGER.info("Clearing events caches");
    }
}
