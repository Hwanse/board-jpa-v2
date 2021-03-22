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
import practical.boardjpa.domain.dto.PostDto;
import practical.boardjpa.domain.dto.QPostDto;
import practical.boardjpa.repository.query.PostRepositoryQuery;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostDto> searchPostPage(PostSearchRequest search, Pageable pageable) {

        List<PostDto> content = queryFactory
            .select(new QPostDto(
                post.id,
                post.title,
                post.contents,
                post.isUse,
                post.writer,
                post.modifier,
                post.parent.id
            ))
            .from(post)
            .where(
                writerEq(search.getWriter()),
                containsTitle(search.getTitle()),
                containsContents(search.getContents()),
                betweenCreateDate(search.getFrom(), search.getTo())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Post> countQuery = queryFactory
            .select(post)
            .from(post)
            .where(
                writerEq(search.getWriter()),
                containsTitle(search.getTitle()),
                containsContents(search.getContents()),
                betweenCreateDate(search.getFrom(), search.getTo())
            );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanBuilder betweenCreateDate(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null) {
            new BooleanBuilder();
        }
        return new BooleanBuilder(post.createDate.between(from, to));
    }

    private BooleanBuilder containsContents(String contents) {
        if (contents == null || !hasText(contents)) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(post.contents.contains(contents));
    }

    private BooleanBuilder containsTitle(String title) {
        if (title == null || !hasText(title)) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(post.title.contains(title));
    }

    private BooleanBuilder writerEq(String writer) {
        if (writer == null || !hasText(writer)) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(post.writer.eq(writer));
    }
}
