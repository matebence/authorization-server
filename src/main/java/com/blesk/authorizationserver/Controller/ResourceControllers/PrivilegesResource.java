package com.blesk.authorizationserver.Controller.ResourceControllers;

import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Service.Privileges.PrivilegesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class PrivilegesResource {

    private PrivilegesServiceImpl privilegesService;

    @Autowired
    public PrivilegesResource(PrivilegesServiceImpl privilegesService) {
        this.privilegesService = privilegesService;
    }

    @GetMapping("/privileges/page/{pageNumber}/limit/{pageSize}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public CollectionModel<List<Privileges>> retrieveAllPrivileges(@PathVariable int pageNumber, @PathVariable int pageSize) {
        List<Privileges> privileges = privilegesService.getAllPrivileges(pageNumber, pageSize);
        CollectionModel<List<Privileges>> collectionModel = new CollectionModel(privileges);

        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllPrivileges(pageNumber, pageSize)).withSelfRel());
        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllPrivileges(++pageNumber, pageSize)).withRel("next-range"));

        return collectionModel;
    }

    @GetMapping("/privileges/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Privileges> retrievePrivileges(@PathVariable long id) {
        Privileges privileges = privilegesService.getPrivilege(id);

        EntityModel<Privileges> EntityModel = new EntityModel<Privileges>(privileges);
        EntityModel.add(linkTo(methodOn(this.getClass()).retrievePrivileges(id)).withSelfRel());
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAllPrivileges(0 ,10)).withRel("all-privileges"));

        return EntityModel;
    }

    @DeleteMapping("/privileges/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePrivileges(@PathVariable long id) {
        privilegesService.deletePrivilege(id);
    }

    @PostMapping("/privileges")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createPrivileges(@Valid @RequestBody Privileges privileges) {
        Privileges privilege = privilegesService.createPrivilege(privileges);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(privilege.getPrivilegeId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/privileges/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updatePrivileges(@Valid @RequestBody Privileges privileges, @PathVariable long id) {
        if (privilegesService.getPrivilege(id) != null) {
            privileges.setPrivilegeId(id);
        }
        privilegesService.updatePrivilege(privileges);
        return ResponseEntity.noContent().build();
    }
}