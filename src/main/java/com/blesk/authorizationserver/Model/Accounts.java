package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Accounts.class)
public class Accounts implements Serializable {

    private Long accountId;

    private Logins login;

    private Passwords passwords;

    private Activations activations;

    private Set<AccountRoles> accountRoles = new HashSet<AccountRoles>();

    private Set<AccountPreferences> accountPreferences = new HashSet<AccountPreferences>();

    private String userName;

    private String email;

    private String password;

    private String confirmPassword;

    private Boolean isActivated;

    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    private HashMap<String, String> validations = new HashMap<>();

    private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    private Collection<String> grantedPrivileges = new ArrayList<>();

    public Accounts(String userName, String email, String password, String confirmPassword, Boolean isActivated, Boolean isDeleted, HashMap<String, String> validations) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isActivated = isActivated;
        this.isDeleted = isDeleted;
        this.validations = validations;
    }

    public Accounts(String userName, String email, String password, String confirmPassword, Boolean isActivated, Boolean isDeleted) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isActivated = isActivated;
        this.isDeleted = isDeleted;
    }

    public Accounts(Long accountId, Activations activations) {
        this.accountId = accountId;
        this.activations = activations;
    }

    public Accounts(Long accountId, Passwords passwords) {
        this.accountId = accountId;
        this.passwords = passwords;
    }

    public Accounts() {
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Logins getLogin() {
        return this.login;
    }

    public void setLogin(Logins login) {
        this.login = login;
    }

    public Passwords getPasswords() {
        return this.passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

    public Activations getActivations() {
        return this.activations;
    }

    public void setActivations(Activations activations) {
        this.activations = activations;
    }

    public void addRole(AccountRoles accountRoles) {
        this.accountRoles.add(accountRoles);
    }

    public void removeRole(AccountRoles accountRoles) {
        this.accountRoles.remove(accountRoles);
    }

    public Set<AccountRoles> getAccountRoles() {
        return this.accountRoles;
    }

    public void addPreference(AccountPreferences accountPreferences) {
        this.accountPreferences.add(accountPreferences);
    }

    public void removePreference(AccountPreferences accountPreferences) {
        this.accountPreferences.remove(accountPreferences);
    }

    public Set<AccountPreferences> getAccountPreferences() {
        return this.accountPreferences;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getActivated() {
        return this.isActivated;
    }

    public void setActivated(Boolean activated) {
        this.isActivated = activated;
    }

    public Boolean getDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public HashMap<String, String> getValidations() {
        return this.validations;
    }

    public void setValidations(HashMap<String, String> validations) {
        this.validations = validations;
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return this.grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Collection<String> getGrantedPrivileges() {
        return this.grantedPrivileges;
    }

    public void setGrantedPrivileges(Collection<String> grantedPrivileges) {
        this.grantedPrivileges = grantedPrivileges;
    }
}