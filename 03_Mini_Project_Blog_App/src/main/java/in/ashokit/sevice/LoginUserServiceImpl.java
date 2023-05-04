package in.ashokit.sevice;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.BlogPage;
import in.ashokit.entity.PostEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repository.PostRepo;
import in.ashokit.repository.UserRepo;

@Service
public class LoginUserServiceImpl implements LoginUserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private HttpSession session;


	@Override
public List<PostEntity> getAllPost(Integer userId) {
		
		Optional<UserEntity> userEntity = userRepo.findById(userId);
		if(userEntity.isPresent()) {
			UserEntity entity = userEntity.get();
			List<PostEntity> posts = entity.getPosts();
			
			return posts;
		}
		return null;
	}


	@Override
	public String addBlog(BlogPage blog, Integer userId) {
		
		PostEntity postEntity = new PostEntity();
		BeanUtils.copyProperties(blog, postEntity);
		
		Optional<UserEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			
			UserEntity userEntity = findById.get();
			postEntity.setUser(userEntity);
		}
		
		postRepo.save(postEntity);
		
		return "Blog Added Successfully";
	}
	


}
