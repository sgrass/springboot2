package org.cx.web;

import org.cx.domain.User;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * @author grass
 * @date 2017/10/21
 */
@RestController
public class UserController {

    @PostMapping("/user/save")
    public Object save(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                map.put("msg", objectError.getCodes()[1]+"-->"+objectError.getDefaultMessage());
            }
            return map;
        }
        return user;
    }

    @PostMapping("/user/save2")
    public User save2(@RequestBody User user) {
        //api的形式
        Assert.hasText(user.getName(), "名称不能为空");

        assert user.getId() <= 100;

        return user;
    }
}
