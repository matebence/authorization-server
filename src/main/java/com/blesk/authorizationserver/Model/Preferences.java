package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Preferences.class)
public class Preferences implements Serializable {

    private Long preferenceId;

    private Set<AccountPreferences> accountPreferences = new HashSet<AccountPreferences>();

    private String name;

    public Preferences(String name) {
        this.name = name;
    }

    public Preferences() {
    }

    public Long getPreferenceId() {
        return this.preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public void addAccount(AccountPreferences accountPreferences) {
        this.accountPreferences.add(accountPreferences);
    }

    public void removeAccount(AccountPreferences accountPreferences) {
        this.accountPreferences.remove(accountPreferences);
    }

    public Set<AccountPreferences> getAccountPreferences() {
        return this.accountPreferences;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}