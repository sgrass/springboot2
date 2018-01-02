package org.cx.service;

import org.cx.entity.Person;
import org.cx.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author grass
 * @date 2018/1/2
 */
@Service
@Transactional
public class PersonService {

    /**
     * 通过标准jpa方式注入
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Person person) {
        entityManager.persist(person);
    }



}
