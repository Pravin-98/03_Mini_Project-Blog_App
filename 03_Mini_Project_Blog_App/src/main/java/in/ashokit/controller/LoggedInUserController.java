package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.BlogPage;
import in.ashokit.binding.Comment;
import in.ashokit.entity.CommentEntity;
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
	public String addBlog(@ModelAttribute("blogPage")BlogPage blogPage,Model model) {
		System.out.println(blogPage);
		Integer userId = (Integer) session.getAttribute("userId");
		String status = loginService.addBlog(blogPage, userId);
		model.addAttribute("succMsg", status);
		return"blogPage";
	}
	
	@GetMapping("/edit")
	public String editBlog(@RequestParam("postId")Integer postId, Model model) {
		BlogPage blog = loginService.getBlog(postId);
		model.addAttribute("blogPage", blog);
		return "blogPage";
	}
	
	@GetMapping("/delete")
	public String deleteBlog(@RequestParam("postId")Integer postId, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<PostEntity> entity = loginService.deleteBlog(postId, userId);
		model.addAttribute("posts", entity);
		return "dashboard";
	}
	
	@GetMapping("/viewBlog")
	public String viewBlog(@RequestParam("title")String title, Model model) {
		PostEntity post = loginService.viweBlog(title);
		List<CommentEntity> comments = post.getComments();
		model.addAttribute("commentForm", new Comment());
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "viewBlogPage";
	}
	
	@PostMapping("/comment")
	public String addComment(@ModelAttribute("commentForm") Comment comment,@RequestParam("title")String title, Model model) {
		System.out.println(comment);
		PostEntity post = loginService.viweBlog(title);
		loginService.addComment(comment, post);
		List<CommentEntity> comments = post.getComments();
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "viewBlogPage";
	}
	
	@GetMapping("/viewComments")
	public String viewComments(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<CommentEntity> comments = loginService.getAllComments(userId);
		model.addAttribute("comments", comments);
		return "viewComment";
	}
	
}
