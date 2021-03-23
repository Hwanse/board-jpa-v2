package practical.boardjpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Comment;
import practical.boardjpa.repository.query.CommentRepositoryQuery;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryQuery {

    @EntityGraph(attributePaths = {"post"})
    public Comment findCommentWithPostById(Long commentId);

}
