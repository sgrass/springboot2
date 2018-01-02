package org.cx.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.cx.domain.Person;
import org.cx.repository.PersonRepository;
import org.cx.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author grass
 * @date 2017/12/29
 */
@RestController
public class PersonProviderController {
    private final Map<Long, Person> persons = new ConcurrentHashMap<>();

    private final static Random random = new Random();

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    /**
     * RequestBody实现参考{@link RequestResponseBodyMethodProcessor}
     * @param person
     * @return
     */
    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
//        return persons.put(person.getId(), person) == null;
        org.cx.entity.Person person1 = new org.cx.entity.Person();
        BeanUtils.copyProperties(person, person1);
        personRepository.save(person1);

        return true;
    }

    @GetMapping(value = "/person/find/all")
    @HystrixCommand(
            fallbackMethod = "fallbackForFindAllPersons",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "100")
            }
    )
    public Collection<Person> findAll() throws InterruptedException {

        //如果随机时间 大于 100 ，那么触发容错
        int value = random.nextInt(200);
        Thread.sleep(value);
        System.out.println("findAllPersons() costs " + value + " ms.");

        return persons.values();
    }

    /**
     * {@link #findAll()} Fallback 方法
     *
     * @return 返回空集合
     */
    public Collection<Person> fallbackForFindAllPersons() {
        System.err.println("fallbackForFindAllPersons() is invoked!");

        return Collections.emptyList();
    }


    /**
     * 分页参数识别参考
     * @see {@link PageableArgumentResolver}
     * @see {@link PageableHandlerMethodArgumentResolver}
     *
     * @param pageable
     * @return
     */
    @GetMapping(value = "/person/list")
    public Page<org.cx.entity.Person> personList(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
