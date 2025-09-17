package com.java.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaAuthorUpdateListener
{
    @Autowired
    private AuthorCacheService cacheService;

    private static final String TOPIC_NAME = "update-cache";

    @KafkaListener(topics = TOPIC_NAME, groupId = "my-group")
    public void getEvent(Long authorId)
    {
        cacheService.evictAuthorCache(authorId);
    }
}
