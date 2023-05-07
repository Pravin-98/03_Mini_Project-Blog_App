package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BlogPage {

	private Integer postId;
	private String title;
	private String discription;
	private String content;
	private LocalDate createdOn;
}
