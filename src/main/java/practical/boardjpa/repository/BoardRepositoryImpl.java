package practical.boardjpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import practical.boardjpa.domain.Board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

}
