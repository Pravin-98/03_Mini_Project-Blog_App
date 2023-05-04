package in.ashokit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="post_tbl")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String title;
	private String discription;
	@Lob
	private String content;
	@CreationTimestamp
	private LocalDate createdOn;
	@UpdateTimestamp
	private LocalDate updatedOn;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserEntity user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<CommentEntity> comments;
}
