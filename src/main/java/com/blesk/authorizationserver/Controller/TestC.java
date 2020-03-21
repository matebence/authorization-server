package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.DAO.Accounts.AccountDAOImpl;
import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TestC {

    private AccountDAOImpl accountDAO;

    @Autowired
    public TestC(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }
//
//    @GetMapping
//    public ResponseEntity<CollectionModel<AccountResource>> all() {
//        final List<AccountResource> collection = accountDAO.getAll(5,10);
//        final CollectionModel<AccountResource> resources = new CollectionModel<>(collection);
//        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
//        resources.add(new Link(uriString, "self"));
//        return ResponseEntity.ok(resources);
//    }

//
//    @RequestMapping("/api")
//    public HttpEntity<Accounts> api() {
//        Accounts accounts = accountDAO.get(1L);
//        accounts.add(linkTo(methodOn(TestC.class).api()).withSelfRel());
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }
}