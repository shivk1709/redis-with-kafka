package com.java.author;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class AuthorCacheServiceImpl implements AuthorCacheService
{

    @Override
    @CacheEvict(value = "authors", key = "#authorId")
    public void evictAuthorCache(Long authorId)
    {
        System.out.println("Cache Evicted Successfully for authorId " + authorId);
    }
}
