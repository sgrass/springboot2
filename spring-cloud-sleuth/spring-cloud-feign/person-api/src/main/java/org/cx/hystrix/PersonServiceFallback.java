package org.cx.hystrix;

import org.cx.domain.Person;
import org.cx.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * @author grass
 * @date 2017/12/29
 */
public class PersonServiceFallback implements PersonService {
    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }

}
