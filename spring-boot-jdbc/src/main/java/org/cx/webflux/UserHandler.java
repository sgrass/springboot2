package org.cx.webflux;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.cx.domain.User;
import org.cx.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author grass
 * @date 2017/10/22
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        // Mono<User> 类似于 Optional<User>
        System.out.printf("[Thread : %s ] UserHandler starts saving user...\n", Thread.currentThread().getName());

        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        //map相当于转化工作  异步的
        Mono<Boolean> booleanMono = userMono.map(userRepository::save);

        return ServerResponse.ok().body(booleanMono, Boolean.class);
    }
}
