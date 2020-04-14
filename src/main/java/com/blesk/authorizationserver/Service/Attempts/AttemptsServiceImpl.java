package com.blesk.authorizationserver.Service.Attempts;

import com.blesk.authorizationserver.DTO.OAuth2.Account;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Logins;
import com.blesk.authorizationserver.Service.Messages.MessagesServiceImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class AttemptsServiceImpl implements AttemptsService {

    @Value("${config.oauth2.max-attempts}")
    private Integer maxAttempts;

    @Autowired
    private MessagesServiceImpl messagesService;

    private LoadingCache<String, Integer> loadingCache;

    public AttemptsServiceImpl() {
        super();
        this.loadingCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String ip) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String ip, Account account) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(account.getAccountId());

        Logins logins = new Logins();
        logins.setLoginId(account.getLoginId());
        logins.setIpAddress(ip);
        logins.setAccounts(accounts);
        logins.setLastLogin(new Timestamp(System.currentTimeMillis()));

        this.messagesService.sendLoginDetails(logins);
        this.loadingCache.invalidate(ip);
    }

    @Override
    public void loginSucceeded(String ip) {
        throw new NotImplementedException();
    }

    @Override
    public void loginFailed(String ip) {
        int attempts = 0;
        try {
            attempts = this.loadingCache.get(ip);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        this.loadingCache.put(ip, attempts);
    }

    @Override
    public boolean isBlocked(String ip) {
        try {
            return this.loadingCache.get(ip) >= this.maxAttempts;
        } catch (ExecutionException e) {
            return false;
        }
    }
}