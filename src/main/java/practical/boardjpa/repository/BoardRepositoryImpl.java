package practical.boardjpa.repository;

import static org.springframework.util.StringUtils.hasText;
import static practical.boardjpa.domain.QPost.post;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import practical.boardjpa.api.request.PostSearchRequest;
import practical.boardjpa.domain.Post;
import practical.boardjpa.repository.query.BoardRepositoryQuery;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryQuery {

    private final JPAQueryFactory queryFactory;

}
