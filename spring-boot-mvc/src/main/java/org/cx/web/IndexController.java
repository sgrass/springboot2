package org.cx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * @author grass
 * @date 2017/10/16
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index() throws InterruptedException {
        return "index";
    }

}
