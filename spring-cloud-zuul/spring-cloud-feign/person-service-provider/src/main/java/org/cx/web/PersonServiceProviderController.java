package org.cx.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.cx.domain.Person;
import org.cx.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link PersonService} 提供者控制器（可选实现{@link PersonService}）
 * @author grass
 * @date 2017/12/23
 */
@RestController
public class PersonServiceProviderController {

    private final Map<Long, Person> persons = new ConcurrentHashMap<>();

    private final static Random random = new Random();


    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return persons.put(person.getId(), person) == null;
    }

    @GetMapping(value = "/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAllPersons",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            }
    )

    public Collection<Person> findAll() throws InterruptedException {
        int value = random.nextInt(200);

        Thread.sleep(value);
        System.out.println("findAllPersons() costs " + value + " ms.");

        return persons.values();
    }

    public Collection<Person> fallbackForFindAllPersons() {
        System.err.println("fallbackForFindAllPersons() is invoked!");
        return Collections.emptyList();
    }
}
