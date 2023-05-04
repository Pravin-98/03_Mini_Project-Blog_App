package in.ashokit.sevice;

import java.util.List;

import in.ashokit.binding.BlogPage;
import in.ashokit.entity.PostEntity;

public interface LoginUserService {
	
	public List<PostEntity> getAllPost(Integer userId);
	
	public String addBlog(BlogPage blog, Integer userId);

}
