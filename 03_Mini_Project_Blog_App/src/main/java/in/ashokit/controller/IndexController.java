package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.entity.PostEntity;
import in.ashokit.sevice.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		List<PostEntity> posts = userService.getAllBlog();
		model.addAttribute("posts", posts);
		return "index";
	}
}
