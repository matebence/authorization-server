package com.blesk.authorizationserver.Model;

import com.blesk.authorizationserver.Values.Messages;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "privileges")
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id")
    private Long privilegeId;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "privileges", fetch = FetchType.LAZY)
    private List<Roles> roles = new ArrayList<Roles>();

    @NotNull(message = Messages.PRIVILEGES_NOT_NULL)
    @Size(min = 3, max = 255, message = Messages.PRIVILEGES_SIZE)
    @Column(name = "name", nullable=false, unique = true)
    private String name;

    @Column(name = "is_deleted", nullable=false)
    private Boolean isDeleted = false;

    @NotNull(message = Messages.ENTITY__CREATOR_ID)
    @Positive(message = Messages.ENTITY_IDS)
    @Column(name = "created_by", nullable=false)
    private Long createdBy;

    @Column(name = "created_at", updatable=false, nullable=false)
    private java.sql.Timestamp createdAt;

    @Positive(message = Messages.ENTITY_IDS)
    @Column(name = "updated_by", updatable=false)
    private Long updatedBy;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Positive(message = Messages.ENTITY_IDS)
    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

    public Privileges() {
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PrePersist
    protected void prePersist() {
        this.isDeleted = false;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Privileges{" +
                "privilegeId=" + privilegeId +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                ", deletedBy=" + deletedBy +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
