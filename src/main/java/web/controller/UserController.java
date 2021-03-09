package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;

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
	public String getUsername(Principal principal, ModelMap model) {
		String name;
		try {
			name = principal.getName();
		} catch (NullPointerException e){
			name = "";
		}

		model.addAttribute("current_user", "Hello, " + name);
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

	/*@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(ModelMap modelMap) {
		modelMap.addAttribute("message", "Sign out of this account?");
		return "login";
	}*/

	/*@RequestMapping(value = "/logged_out", method = RequestMethod.POST, )
	public String logoutSuccess(){
		return "/hello";
	}*/

    @RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(Principal principal, ModelMap modelMap) {
		modelMap.addAttribute("current_user",
				userService.getByName(principal.getName()));
		return "user";
	}

}