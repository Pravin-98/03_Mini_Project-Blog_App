package in.ashokit.sevice;

import java.util.List;

import in.ashokit.binding.BlogPage;
import in.ashokit.binding.Comment;
import in.ashokit.entity.CommentEntity;
import in.ashokit.entity.PostEntity;

public interface LoginUserService {
	
	public List<PostEntity> getAllPost(Integer userId);
	
	public String addBlog(BlogPage blog, Integer userId);
	
	public BlogPage getBlog(Integer postId);
	
	public List<PostEntity> deleteBlog(Integer postId, Integer userId);
	
	public PostEntity viweBlog(String title);
	
	public CommentEntity addComment(Comment comment,PostEntity post);
	
	public List<CommentEntity> getAllComments(Integer userId);
	

}
