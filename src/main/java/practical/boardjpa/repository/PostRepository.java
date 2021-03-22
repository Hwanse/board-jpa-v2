package practical.boardjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
