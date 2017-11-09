package org.cx.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.cx.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author grass
 * @date 2017/10/19
 */
@Repository
public class UserRepository {

    private ConcurrentMap<Long, User> repository = new ConcurrentHashMap<>();

    private AtomicInteger idGenerator = new AtomicInteger();

    public Boolean save(User user) {
        // ID 从 1 开始
        long id = idGenerator.incrementAndGet();
        // 1 -> user
        // 1 -> user1 -> user return
        user.setId(id);
        return repository.put(id, user) == null;
    }

    // Effective Java II
    public Collection<User> findAll() {
        return repository.values();
    }
}
