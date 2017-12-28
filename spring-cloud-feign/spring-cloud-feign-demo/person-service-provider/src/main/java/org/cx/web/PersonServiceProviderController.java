package org.cx.web;

import org.cx.domain.Person;
import org.cx.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link PersonService} 提供者控制器（可选实现{@link PersonService}）
 *
 * @author grass
 * @date 2017/12/4
 */
@RestController
public class PersonServiceProviderController {

    private final ConcurrentHashMap<Long, Person> persoMap = new ConcurrentHashMap();

    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return persoMap.put(person.getId(), person) == null;
    }

    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAll() {
        return persoMap.values();
    }
}
