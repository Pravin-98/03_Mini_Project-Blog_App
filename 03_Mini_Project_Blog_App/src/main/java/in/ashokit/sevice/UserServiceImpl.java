package in.ashokit.sevice;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.Login;
import in.ashokit.binding.Register;
import in.ashokit.entity.PostEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repository.PostRepo;
import in.ashokit.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private HttpSession session;
	
	@Override
	public String registerUser(Register register) {
		
	// checking user is already exist of not.
		UserEntity findByEmail = userRepo.findByEmail(register.getEmail());
		if(null != findByEmail) {
			return "With This Email  Account is already Created Plase Login";
		}
		
	//	Convert form to entity and Save it.
		try {
			UserEntity entity = new UserEntity();
			BeanUtils.copyProperties(register, entity);
			
			userRepo.save(entity);
			return "Account Created Successfully";			
		} catch (Exception e) {
			e.printStackTrace();
			return "Something Went Wrong";
		}
			
	}

	@Override
	public String loginUser(Login login) {
		
		UserEntity entity = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (entity == null) {
			return "Invalid Credentials";
		}
		
		session.setAttribute("userId", entity.getUserId());
		return "Success";
	}

	
	
	

}
