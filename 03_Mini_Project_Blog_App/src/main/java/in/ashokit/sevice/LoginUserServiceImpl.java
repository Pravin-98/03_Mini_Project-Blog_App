package in.ashokit.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.BlogPage;
import in.ashokit.binding.Comment;
import in.ashokit.entity.CommentEntity;
import in.ashokit.entity.PostEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repository.CommentRepo;
import in.ashokit.repository.PostRepo;
import in.ashokit.repository.UserRepo;

@Service
public class LoginUserServiceImpl implements LoginUserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commRepo;
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


	@Override
	public BlogPage getBlog(Integer postId) {
		BlogPage blogPage = new BlogPage();
		Optional<PostEntity> findById = postRepo.findById(postId);
		if (findById.isPresent()) {
			PostEntity postEntity = findById.get();
			BeanUtils.copyProperties( postEntity,blogPage );
		}
		return blogPage;
	}


	@Override
	public List<PostEntity> deleteBlog(Integer postId, Integer userId) {
		
		Optional<PostEntity> findById = postRepo.findById(postId);
		if (findById.isPresent()) {
			postRepo.deleteById(postId);
		}
		List<PostEntity> allPost = getAllPost(userId);
		return allPost;
	}


	@Override
	public PostEntity viweBlog(String title) {
		PostEntity findByTitle = postRepo.findByTitle(title);
		if (findByTitle != null) {
			return findByTitle;
		}		
		return null;
	}

	
	@Override
	public CommentEntity addComment(Comment comment, PostEntity post) {
		CommentEntity entity = new CommentEntity();
		BeanUtils.copyProperties(comment, entity);		
		entity.setPost(post);
		commRepo.save(entity);		
		return entity;
	}


	@Override
	public List<CommentEntity> getAllComments(Integer userId) {
		Optional<UserEntity> findById = userRepo.findById(userId);
		List<CommentEntity> comments = new ArrayList<>() ;
		if (findById.isPresent()) {
			
			UserEntity userEntity = findById.get();
			List<PostEntity> posts = userEntity.getPosts();
			for (PostEntity post : posts) {
				 List<CommentEntity> list = post.getComments();
				for (CommentEntity comm : list) {
					comments.add(comm);
				}
				 
			}
		}
		return comments;
	}
	
	
	


}
