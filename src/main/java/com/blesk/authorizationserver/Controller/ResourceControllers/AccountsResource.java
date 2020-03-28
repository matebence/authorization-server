package com.blesk.authorizationserver.Controller.ResourceControllers;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Service.Accounts.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/accounts/page/{pageNumber}/limit/{pageSize}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public CollectionModel<List<Accounts>> retrieveAllAccounts(@PathVariable int pageNumber, @PathVariable int pageSize) {
        List<Accounts> accounts = accountsService.getAllAccounts(pageNumber, pageSize);
        CollectionModel<List<Accounts>> collectionModel = new CollectionModel(accounts);

        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllAccounts(pageNumber, pageSize)).withSelfRel());
        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllAccounts(++pageNumber, pageSize)).withRel("next-range"));

        return collectionModel;
    }

    @GetMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Accounts> retrieveAccounts(@PathVariable long id) {
        Accounts accounts = accountsService.getAccount(id);

        EntityModel<Accounts> entityModel = new EntityModel<Accounts>(accounts);
        entityModel.add(linkTo(methodOn(this.getClass()).retrieveAccounts(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(this.getClass()).retrieveAllAccounts(0, 10)).withRel("all-accounts"));

        return entityModel;
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccounts(@PathVariable long id) {
        accountsService.deleteAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccounts(@Valid @RequestBody Accounts accounts, @RequestBody ArrayList<String> roles) {
        Accounts account = accountsService.createAccount(accounts, roles);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getAccountId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateAccounts(@Valid @RequestBody Accounts accounts, @PathVariable long id) {
        if (accountsService.getAccount(id) != null) {
            accounts.setAccountId(id);
        }
        accountsService.updateAccount(accounts);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/accounts/search")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<List<Accounts>> searchForAccounts(@RequestBody HashMap<String, HashMap<String, String>> search) {
        Map<String, Object> accounts = accountsService.searchForAccount(search);

        CollectionModel<List<Accounts>> collectionModel = new CollectionModel((List<Accounts>) accounts.get("results"));
        collectionModel.add(linkTo(methodOn(this.getClass()).searchForAccounts(search)).withSelfRel());

        if ((boolean) accounts.get("hasPrev")) {
            collectionModel.add(linkTo(methodOn(this.getClass()).searchForAccounts(search)).withRel("hasPrev"));
        }
        if ((boolean) accounts.get("hasNext")) {
            collectionModel.add(linkTo(methodOn(this.getClass()).searchForAccounts(search)).withRel("hasNext"));
        }

        return collectionModel;
    }
}