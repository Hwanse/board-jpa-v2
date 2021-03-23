package practical.boardjpa.repository;

import static practical.boardjpa.domain.QComment.comment;
import static practical.boardjpa.domain.QPost.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import practical.boardjpa.domain.dto.CommentDto;
import practical.boardjpa.domain.dto.QCommentDto;
import practical.boardjpa.repository.query.CommentRepositoryQuery;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDto> findAllCommentDtoWithPost() {
        return queryFactory
            .select(new QCommentDto(
                comment.id,
                comment.contents,
                comment.post.id,
                comment.writer,
                comment.modifier,
                comment.isUse,
                comment.parent.id,
                comment.level
            ))
            .from(comment)
            .leftJoin(comment.post, post)
            .fetch();
    }

    @Override
    public List<CommentDto> selectedPostComments(Long postId) {
        return queryFactory
            .select(new QCommentDto(
                comment.id,
                comment.contents,
                comment.post.id,
                comment.writer,
                comment.modifier,
                comment.isUse,
                comment.parent.id,
                comment.level
            ))
            .from(comment)
            .leftJoin(comment.post, post)
            .where(comment.post.id.eq(postId))
            .fetch();
    }


}
