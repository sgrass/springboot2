package org.cx.service;

import org.cx.domain.Person;
import org.cx.hystrix.PersonServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * @author grass
 * @date 2017/12/4
 */
@FeignClient(value = "person-service", fallback = PersonServiceFallback.class)
public interface PersonService {

    @PostMapping(value = "/person/save")
    boolean save(@RequestBody Person person);

    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll();
}
