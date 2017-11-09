package org.cx.web;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.cx.domain.User;
import org.cx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author grass
 * @date 2017/10/22
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/web/mvc/user/save")
    public Boolean save(@RequestBody User user) {
        System.out.printf("[Thread : %s] UserController starts saving user...\n", Thread.currentThread().getName());
        return userRepository.transactionalSave(user);
    }

    @PostMapping("/web/mvc/user/save2")
    public Boolean save2(@RequestBody User user) throws ExecutionException, InterruptedException {
        Future<Boolean> future = executorService.submit(() -> {
            return userRepository.save(user);
        });
        return future.get();
    }
}
