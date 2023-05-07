package in.ashokit.sevice;

import java.util.List;

import in.ashokit.binding.Login;
import in.ashokit.binding.Register;
import in.ashokit.entity.PostEntity;

public interface UserService {

	public String registerUser(Register register);
	
	public String loginUser(Login login);
	
	public List<PostEntity> getAllBlog();

	
}
