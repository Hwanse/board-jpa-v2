package practical.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuery{

}
