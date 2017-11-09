package org.cx.web;

import org.cx.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author grass
 * @date 2017/10/17
 */
@RestController
public class UserRestController {

    /**
     * 依赖jackson-dataformat-xml 包
     *
     * 客户端请求设置accept=application/json则返回json，application/xml则返回xml
     *
     * Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*//*;q=0.8
     * 浏览器请求accept会根据权重因子q=0.9..顺序的匹配
     * @param id
     * @param name
     * @return
     */
    @GetMapping(value = "user/{id}")
    public User user(@PathVariable Long id, @PathVariable(required = false) String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    @PostMapping(value = "/person/json/to/properties",
            consumes = "text/plain",
            produces = "application/properties+person" // 响应类型
    )
    public User personJsonToProperties(@RequestParam String json) {

        // JSON to Map
        // Map to Properties
        return null;
    }

    @PostMapping(value = "/user/json/to/properties", produces = "application/properties+user")
    public User userJsonToProperty(@RequestBody User user) {
        //produces 响应类型处理为 properties+user
        return user;
    }

    @PostMapping(value = "user/properties/to/json", consumes = "application/properties+user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User userPropertiesToJson(@RequestBody User user) {
        /**
         * consumes 请求content-Type设置为properties+user
         * produces 响应设置为application/json;charset=UTF-8
         *
         *
         * consumes 匹配content-Type
         * produces匹配accept
         */
        return user;
    }
}
