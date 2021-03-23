package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;

@Controller
public class ErrorController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("userForm", new User());
        return "errorpage/403";
    }
}
