package practical.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
