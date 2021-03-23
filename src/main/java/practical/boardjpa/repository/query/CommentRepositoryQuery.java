package practical.boardjpa.repository.query;

import java.util.List;
import practical.boardjpa.domain.dto.CommentDto;

public interface CommentRepositoryQuery {

    List<CommentDto> findAllCommentDtoWithPost();

    List<CommentDto> selectedPostComments(Long postId);

}
