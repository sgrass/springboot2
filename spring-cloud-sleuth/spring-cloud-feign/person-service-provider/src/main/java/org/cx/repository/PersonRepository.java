package org.cx.repository;

import org.cx.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author grass
 * @date 2018/1/2
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
