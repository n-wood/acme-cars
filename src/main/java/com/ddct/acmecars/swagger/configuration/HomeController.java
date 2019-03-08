package com.ddct.acmecars.swagger.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {


    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/swaggerui")
    public String swaggerui() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }



}
