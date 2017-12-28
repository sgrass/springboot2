package org.cx.web;

import org.cx.domain.Person;
import org.cx.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author grass
 * @date 2017/12/23
 */
@RestController
public class PersonController implements PersonService{

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean save(@RequestBody Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
