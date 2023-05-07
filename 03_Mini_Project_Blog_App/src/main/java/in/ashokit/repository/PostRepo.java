package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.PostEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {
	
	public PostEntity findByTitle(String title);

}
