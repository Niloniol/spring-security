package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin/getAllUsers")
    public String getAllUsers(){
        return "admin/admin";
    }

    @ModelAttribute(value = "userEntity")
    public User newEntity() {
        return new User();
    }

    @ModelAttribute(value = "user_list")
    public List<User> userList() {
        return userService.listUsers();
    }

    @ModelAttribute(value = "role_list")
    public List<Role> roleList() {
        return roleService.listRoles();
    }

    @RequestMapping(value = "/admin/actionUser", params = {"btn-del"})
    public String deleteUser(final HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("btn-del"));
        userService.remove(user);
        return "redirect:/admin/getAllUsers";
    }

    @RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
    public ModelAndView newUser() {

        List<Role> roleList = roleService.listRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./admin/new_user_form");
        modelAndView.addObject("rolesReq", new ArrayList<Role>());
        modelAndView.addObject("role_list", roleList);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("userEntity") User user) {
        userService.add(user, user.getRoles());
        return "redirect:/admin/newUser";
    }

    @RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
    public String updateUsers(@ModelAttribute("user") User user,
                              ModelMap modelMap) {

        userService.update(user, user.getRoles());

        return "redirect:/admin/getAllUsers";
    }
}
