package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.security.util.Password;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UpdateController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Long userId;

    @RequestMapping(value = "/setUser", method = RequestMethod.GET)
    public ModelAndView user(@ModelAttribute("entity") final User user) {

        List<Role> roleList = roleService.listRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user_update_form");
        modelAndView.addObject("roles", new ArrayList<>());
        modelAndView.addObject("role_list", roleList);

        userId = user.getId();
        return modelAndView;
    }

    @ModelAttribute(value = "rEntity")
    public User newEntity() {
        return new User();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUsers(@ModelAttribute("rEntity") User user,
                              @ModelAttribute("roles") ArrayList<Role> roles,
                              ModelMap modelMap) {

        user.setId(userId);
        userService.update(user, roles);
        userId = null;

        return "redirect:/admin/getAllUsers";
    }
}
