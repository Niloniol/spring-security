package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, ModelMap model) {

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("error", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.add(userForm)){
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/hello";
    }
}
