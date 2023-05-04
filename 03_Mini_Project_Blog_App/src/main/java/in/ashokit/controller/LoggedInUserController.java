package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.sevice.UserService;

@Controller
public class LoggedInUserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public String dashboardPage() {
		
		return"dashboard";
	}
}
