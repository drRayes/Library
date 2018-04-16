package com.rayes.model.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rayes.model.entity.Person;
import com.rayes.model.entity.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonRoleNode {

    private Person person;
    private Role role;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
