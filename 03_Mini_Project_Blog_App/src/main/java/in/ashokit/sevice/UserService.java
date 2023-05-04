package in.ashokit.sevice;

import in.ashokit.binding.Login;
import in.ashokit.binding.Register;

public interface UserService {

	public String registerUser(Register register);
	
	public String loginUser(Login login);
}
