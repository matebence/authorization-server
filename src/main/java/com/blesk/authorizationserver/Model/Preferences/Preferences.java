package com.blesk.authorizationserver.Model.Preferences;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Preferences implements Serializable {

    private Long preferenceId;

    private Set<AccountPreferenceItems> accountPreferenceItems = new HashSet<>();

    private String name;

    public Preferences() {
    }

    public Long getPreferenceId() {
        return this.preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Set<AccountPreferenceItems> getAccountPreferenceItems() {
        return this.accountPreferenceItems;
    }

    public void setAccountPreferenceItems(Set<AccountPreferenceItems> accountPreferenceItems) {
        this.accountPreferenceItems = accountPreferenceItems;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}