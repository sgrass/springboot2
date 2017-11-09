package org.cx.web;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.cx.model.User;
import org.cx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/10/19
 */
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }
}
