package practical.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practical.boardjpa.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
