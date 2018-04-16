package com.rayes.web.controller;

import com.rayes.model.entity.Person;
import com.rayes.model.entity.Role;
import com.rayes.model.node.PersonRoleNode;
import com.rayes.model.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminRest")
public class AdminController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/getPerson/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public@ResponseBody Person getPerson(@PathVariable(name = "login") String login) {
        return personService.getPersonByLogin(login);
    }

    @PostMapping(value = "/updatePerson")
    public String updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @PostMapping(value = "/createPerson")
    public String createPerson(@RequestBody PersonRoleNode node) {
        System.out.println(node.getPerson().getLogin());
        System.out.println(node.getRole().getRole());
        return personService.adminCreatePerson(node.getPerson(), node.getRole());
    }

    @RequestMapping(value = "/listOfPersons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public@ResponseBody List<Person> getListOfPersons() {
        return personService.getListOfPersons();
    }
}