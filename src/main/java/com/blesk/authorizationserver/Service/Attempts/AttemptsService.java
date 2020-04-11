package com.blesk.authorizationserver.Service.Attempts;

public interface AttemptsService {

    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
