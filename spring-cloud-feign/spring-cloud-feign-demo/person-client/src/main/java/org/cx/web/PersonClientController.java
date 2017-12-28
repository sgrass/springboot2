package org.cx.web;

import org.cx.domain.Person;
import org.cx.service.PersonService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 *
 * {@link PersonClientController} 实现 {@link PersonService}
 *
 * @author grass
 * @date 2017/12/4
 */
@RestController
public class PersonClientController implements PersonService {

    private final PersonService personService;

    public PersonClientController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean save(Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
