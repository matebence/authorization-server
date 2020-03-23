package com.blesk.authorizationserver.Controller.ResourceControllers;

import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Service.Roles.RolesServiceImpl;
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
public class RolesResource {

    private RolesServiceImpl rolesService;

    @Autowired
    public RolesResource(RolesServiceImpl rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles/page/{pageNumber}/limit/{pageSize}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public CollectionModel<List<Roles>> retrieveAllRoles(@PathVariable int pageNumber, @PathVariable int pageSize) {
        List<Roles> roles = rolesService.getAllRoles(pageNumber, pageSize);
        CollectionModel<List<Roles>> collectionModel = new CollectionModel(roles);

        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllRoles(pageNumber, pageSize)).withSelfRel());
        collectionModel.add(linkTo(methodOn(this.getClass()).retrieveAllRoles(++pageNumber, pageSize)).withRel("next-range"));

        return collectionModel;
    }

    @GetMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Roles> retrieveRoles(@PathVariable long id) {
        Roles roles = rolesService.getRole(id);

        EntityModel<Roles> EntityModel = new EntityModel<Roles>(roles);
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveRoles(id)).withSelfRel());
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAllRoles(0,10)).withRel("all-roles"));

        return EntityModel;
    }

    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoles(@PathVariable long id) {
        rolesService.deleteRole(id);
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createRoles(@Valid @RequestBody Roles roles) {
        Roles role = rolesService.createRole(roles);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(role.getRoleId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateRoles(@Valid @RequestBody Roles roles, @PathVariable long id) {
        if (rolesService.getRole(id) != null) {
            roles.setRoleId(id);
        }
        rolesService.updateRole(roles);
        return ResponseEntity.noContent().build();
    }
}