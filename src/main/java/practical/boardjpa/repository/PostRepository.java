package practical.boardjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Post;
import practical.boardjpa.repository.query.PostRepositoryQuery;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryQuery {

}
