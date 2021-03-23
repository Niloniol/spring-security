package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping
	public String getPage() {
		return "hello";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getUsername(Authentication authentication, ModelMap model) {

		model.addAttribute("current_user", authentication.getName());
		model.addAttribute("user_authorities", authentication.getAuthorities());

		if(authentication.getAuthorities().stream()
				.anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))) {
			model.addAttribute("source", "./admin/getAllUsers");
		} else if (authentication.getAuthorities().stream()
				.anyMatch(x -> x.getAuthority().equals("ROLE_USER"))){
			model.addAttribute("source", "./user");
		}
		return "hello";
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(required = false) String logout,
							ModelMap modelMap) {
        if(logout != null && logout.equals("logout")){
        	modelMap.addAttribute("message", "You are logged out");
		}

		if(roleService.listRoles().isEmpty()){
			roleService.add(new Role("ROLE_USER"));
			roleService.add(new Role("ROLE_ADMIN"));
		}

		return "login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(Principal principal, ModelMap modelMap) {
		modelMap.addAttribute("current_user",
				userService.getByName(principal.getName()));
		return "user";
	}

}