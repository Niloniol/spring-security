package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.Set;

@Controller
public class UpdateController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    private Long userId;

    @RequestMapping(value = "/setUser", method = RequestMethod.GET)
    public ModelAndView user(@ModelAttribute("entity") final User user) {
        Set<Role> roleSet = (Set<Role>) context.getBean("rolesSet");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user_update_form");
        String userRole = null;
        modelAndView.addObject("user_role", userRole);
        String adminRole = null;
        modelAndView.addObject("admin_role", adminRole);
        modelAndView.addObject("role_set", roleSet);

        userId = user.getId();
        return modelAndView;
    }

    @ModelAttribute(value = "rEntity")
    public User newEntity() {
        return new User();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUsers(@RequestParam(required = false) String str,
                              @ModelAttribute("rEntity") User user,
                              ModelMap modelMap) {

        if(!str.equals("cancel")) {
            userService = context.getBean(UserService.class);
            user.setId(userId);
            userService.update(user);
            userId = null;
        }
        return "admin/admin";
    }
}
