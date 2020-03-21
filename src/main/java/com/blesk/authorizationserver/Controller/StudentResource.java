package com.blesk.authorizationserver.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import com.blesk.authorizationserver.DAO.Accounts.AccountDAOImpl;
import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StudentResource {

    private AccountDAOImpl accountDAO;

    @Autowired
    public StudentResource(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }


    @GetMapping("/accounts")
    public List<Accounts> retrieveAllStudents() {
        return accountDAO.getAll(1,100);
    }

    @GetMapping("/accounts/{id}")
    public EntityModel<Accounts> retrieveAccount() {
        Accounts accounts = accountDAO.get(1L);

        EntityModel<Accounts> EntityModel = new EntityModel<Accounts>(accounts);

        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAllStudents()).withSelfRel());

        return EntityModel;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteStudent() {
        accountDAO.delete(accountDAO.get(1L));
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Accounts accounts) {
        Accounts savedStudent = accountDAO.save(accounts);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getAccountId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Accounts accounts, @PathVariable long id) {

        Accounts studentOptional = accountDAO.get(id);

        accounts.setAccountId(id);

        accountDAO.save(accounts);

        return ResponseEntity.noContent().build();
    }
}