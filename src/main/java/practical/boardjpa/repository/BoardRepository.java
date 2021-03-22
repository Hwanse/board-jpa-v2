package practical.boardjpa.repository;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.api.request.PostSearchRequest;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.Post;
import practical.boardjpa.repository.query.BoardRepositoryQuery;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuery {

}
