package in.ashokit.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Login {
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email...")
	private String email;
	
	@NotBlank(message="Password con not be Empty")
	@Size(min=5,max=15,message="Password must be between 5-15 charecters")
	private String password;

}
