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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model){

        List<User> userList = userService.listUsers();
        model.addAttribute("user_list", userList);
        return "admin/admin";
    }

    /*@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
    public String getPage(){
        return "admin/admin";
    }*/

    @ModelAttribute(value = "userEntity")
    public User newEntity() {
        return new User();
    }

    /*@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("userEntity") User user) {
        userService.add(user);
        return "admin/admin";
    }*/

    @RequestMapping(value = "/admin/actionUser", method = RequestMethod.POST, params = "action=Delete")
    public String deleteUser(@ModelAttribute("userEntity") User user) {
        userService.remove(user);
        return "redirect:/admin/getAllUsers";
    }


    @RequestMapping(value = "/admin/actionUser", method = RequestMethod.POST, params = "action=Update or Add")
    public String updateUser(@ModelAttribute("userEntity") final User user,
                             final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("entity", user);
        return "redirect:/setUser";
    }
}
