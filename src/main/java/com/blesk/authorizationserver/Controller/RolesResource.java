package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Service.Roles.RolesServiceImpl;
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
public class RolesResource {

    private RolesServiceImpl rolesService;

    @Autowired
    public RolesResource(RolesServiceImpl rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Roles> retrieveAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Roles> retrieveRoles(@PathVariable long id) {
        Roles roles = rolesService.getRole(id);

        EntityModel<Roles> EntityModel = new EntityModel<Roles>(roles);
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveRoles(id)).withSelfRel());
        EntityModel.add(linkTo(methodOn(this.getClass()).retrieveAllRoles()).withRel("all-roles"));

        return EntityModel;
    }

    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoles(@PathVariable long id) {
        rolesService.deleteRole(id);
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createRoles(@RequestBody Roles roles) {
        Roles role = rolesService.createRole(roles);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(role.getRoleId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateRoles(@RequestBody Roles roles, @PathVariable long id) {
        if (rolesService.getRole(id) != null) {
            roles.setRoleId(id);
        }
        rolesService.updateRole(roles);
        return ResponseEntity.noContent().build();
    }
}