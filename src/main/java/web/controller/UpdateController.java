package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

@Controller
public class UpdateController {

    @Autowired
    private ApplicationContext context;
    private Long userId;

    @RequestMapping(value = "/setUser", method = RequestMethod.GET)
    public String user(@ModelAttribute("entity") final User user) {
        userId = user.getId();
        return "user_update_form";
    }

    @ModelAttribute(value = "rEntity")
    public User newEntity() {
        return new User();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUsers(@ModelAttribute("rEntity") User user) {
        UserService userService = context.getBean(UserService.class);
        user.setId(userId);
        userService.update(user);

        return "redirect:/admin";
    }
}
