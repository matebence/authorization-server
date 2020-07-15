package com.blesk.authorizationserver.Service.Attempts;

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

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class AttemptsServiceImpl implements AttemptsService {

    @Value("${config.oauth2.max-attempts}")
    private Integer maxAttempts;

    @Value("${config.oauth2.block-account}")
    private Integer blockAccount;

    @Autowired
    private MessagesServiceImpl messagesService;

    private LoadingCache<String, Integer> loadingCache;

    @PostConstruct
    public void init() {
        this.loadingCache = CacheBuilder.newBuilder().expireAfterWrite(this.blockAccount, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            public Integer load(String ip) {
                return 0;
            }
        });
    }

    public AttemptsServiceImpl() {
        super();
    }

    public void loginSucceeded(String ip, Accounts account) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(account.getAccountId());

        Logins logins = new Logins();
        logins.setLoginId(account.getLogin().getLoginId());
        logins.setIpAddress(ip);
        logins.setAccounts(accounts);

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