package org.cx.service;

import org.cx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author grass
 * @date 2017/11/13
 */
@Service
public class UserServiceProxy implements UserService {

    private static final String PROVIDER_SERVER_URL_PREFIX ="http://user-service-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean save(User user) {
        User returnValue = restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX.concat("/user/save"), user, User.class);
        return returnValue != null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX.concat("/user/list"), Collection.class);
    }
}
