package in.ashokit.binding;

import java.time.LocalDate;

import in.ashokit.entity.PostEntity;
import lombok.Data;

@Data
public class Comment {

	private Integer id;
	private String name;
	private String email;	
	private String content;
	private LocalDate createdOn;
	private PostEntity post;
}
