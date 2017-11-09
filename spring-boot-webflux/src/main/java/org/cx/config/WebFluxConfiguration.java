package org.cx.config;

import org.cx.model.User;
import org.cx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author grass
 * @date 2017/10/19
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionAllUsers(UserRepository userRepository) {
        Collection<User> users = userRepository.findAll();
        Flux<User> userFlux = Flux.fromIterable(users);
        return RouterFunctions.route(RequestPredicates.path("/all-user"),
                request -> ServerResponse.ok().body(userFlux, User.class));
    }

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionUsers(UserRepository userRepository) {
        Collection<User> users = userRepository.findAll();
        Flux<User> userFlux = Flux.fromIterable(users);
//        Mono<Collection<User>> mono = Mono.just(users);

        return RouterFunctions.route(RequestPredicates.path("/users"),
                request -> ServerResponse.ok().body(userFlux, User.class));

    }

    /*
     public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
     return route(GET("/{user}").and(accept(APPLICATION_JSON)), userHandler::getUser)
     .andRoute(GET("/{user}/customers").and(accept(APPLICATION_JSON)), userHandler::getUserCustomers)
     .andRoute(DELETE("/{user}").and(accept(APPLICATION_JSON)), userHandler::deleteUser);
     }

     }

     @Component
     public class UserHandler {

     public Mono<ServerResponse> getUser(ServerRequest request) {
     // ...
     }

     public Mono<ServerResponse> getUserCustomers(ServerRequest request) {
     // ...
     }

     public Mono<ServerResponse> deleteUser(ServerRequest request) {
     // ...
     }
     }
     */

}
