package in.ashokit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Login;
import in.ashokit.binding.Register;
import in.ashokit.sevice.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/registerPage")
	public String registerPage(Model model){
		model.addAttribute("register", new Register());
		return "registration";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("register") Register register,BindingResult result ,Model model){
		if (result.hasErrors()) {
			System.out.println(result);
			return "registration";
		}		
		String status = userService.registerUser(register);
		model.addAttribute("succMsg",status);
		return "registration";
	}
	
	@GetMapping("/loginpage")
	public String loginFrom(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") Login login,BindingResult result ,Model model){
		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}
		String status = userService.loginUser(login);
		if (status.contains("Success")) {
			return"redirect:/dashboard";
		}
		model.addAttribute("msg",status);
		return "login";
	}
}
