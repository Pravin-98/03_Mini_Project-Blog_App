package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.BlogPage;
import in.ashokit.entity.PostEntity;
import in.ashokit.sevice.LoginUserService;

@Controller
public class LoggedInUserController {
		
	@Autowired
	private LoginUserService loginService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboardpage")
	public String dashboardPage(Model model) {
		Integer userId =(Integer) session.getAttribute("userId");
		List<PostEntity> allPost = loginService.getAllPost(userId);
		model.addAttribute("posts", allPost);
		return"dashboard";
	}
	
	@GetMapping("/blogPage")
	public String blogPage(Model model) {
		model.addAttribute("blogPage", new BlogPage());
		return"blogPage";
	}
	
	@PostMapping("/blog")
	public String blog(@ModelAttribute("blogPage")BlogPage blogPage,Model model) {
		System.out.println(blogPage);
		Integer userId = (Integer) session.getAttribute("userId");
		String status = loginService.addBlog(blogPage, userId);
		model.addAttribute("succMsg", status);
		return"blogPage";
	}
	
	
	
}
