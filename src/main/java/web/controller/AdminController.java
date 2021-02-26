package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model){
        UserService userService = context.getBean(UserService.class);

        List<User> userList = userService.listUsers();
        model.addAttribute("user_list", userList);
        return "admin";
    }

    @ModelAttribute(value = "userEntity")
    public User newEntity() {
        return new User();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("userEntity") User user) {
        UserService userService = context.getBean(UserService.class);
        userService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/actionUser", method = RequestMethod.POST, params = "action=Delete")
    public String deleteUser(@ModelAttribute("userEntity") User user) {
        UserService userService = context.getBean(UserService.class);
        userService.remove(user);
        return "redirect:/admin";
    }


    @RequestMapping(value = "/actionUser", method = RequestMethod.POST, params = "action=Update or Add")
    public String updateUser(@ModelAttribute("userEntity") final User user,
                             final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("entity", user);
        return "redirect:/setUser";
    }
}
