package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Service.Accounts.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AccountsResource {

    private AccountsServiceImpl accountsService;

    @Autowired
    public AccountsResource(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Accounts> retrieveAllAccounts() {
        return accountsService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Accounts> retrieveAccounts(@PathVariable long id) {
        Accounts accounts = accountsService.getAccount(id);

        EntityModel<Accounts> EntityModel = new EntityModel<Accounts>(accounts);
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAccounts(id)).withSelfRel());
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAllAccounts()).withRel("all-accounts"));

        return EntityModel;
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccounts(@PathVariable long id) {
        accountsService.deleteAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccounts(@RequestBody Accounts accounts) {
        Accounts account = accountsService.createAccount(accounts, new String[]{"ROLE_ADMIN"});

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getAccountId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateAccounts(@RequestBody Accounts accounts, @PathVariable long id) {
        if (accountsService.getAccount(id) != null) {
            accounts.setAccountId(id);
        }
        accountsService.updateAccount(accounts);
        return ResponseEntity.noContent().build();
    }
}