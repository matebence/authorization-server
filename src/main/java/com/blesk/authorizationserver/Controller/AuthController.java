package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Service.Accounts.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
public class AuthController {

    private AccountsServiceImpl accountsService;

    @Autowired
    public AuthController(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/app/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void startRegistration(@Valid @RequestBody Accounts accounts){

    }

    @PostMapping("/app/forgot")
    @ResponseStatus(HttpStatus.CREATED)
    public void retrieveForgetPassword(@RequestBody String email){

    }
}