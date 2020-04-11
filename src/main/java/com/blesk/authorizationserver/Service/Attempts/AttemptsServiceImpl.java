package com.blesk.authorizationserver.Service.Attempt;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class AttemptServiceImpl implements AttemptService {

    @Value("${config.oauth2.max-attempts}")
    private Integer maxAttempts;

    private LoadingCache<String, Integer> loadingCache;

    public AttemptServiceImpl() {
        super();
        this.loadingCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    @Override
    public void loginSucceeded(String key) {
        this.loadingCache.invalidate(key);
    }

    @Override
    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = this.loadingCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        this.loadingCache.put(key, attempts);
    }

    @Override
    public boolean isBlocked(String key) {
        try {
            return this.loadingCache.get(key) >= this.maxAttempts;
        } catch (ExecutionException e) {
            return false;
        }
    }
}