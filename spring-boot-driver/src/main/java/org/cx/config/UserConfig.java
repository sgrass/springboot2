package org.cx.config;

import org.cx.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author grass
 * @date 2017/11/5
 */
@Configuration
public class UserConfig {

    @Bean(name = "user")
    public User user() {
        User user = new User();
        user.setName("grass123");
        return user;
    }
}
