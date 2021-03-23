package practical.boardjpa.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.domain.Comment;
import practical.boardjpa.domain.Post;
import practical.boardjpa.domain.dto.CommentDto;
import practical.boardjpa.repository.CommentRepository;
import practical.boardjpa.repository.PostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Transactional
    public Long createComment(CommentDto commentDto) {
        Post findPost = postRepository.findById(commentDto.getPostId())
                                      .orElseThrow(IllegalAccessError::new);

        Comment comment = new Comment(findPost, commentDto);

        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long createReplyComment(CommentDto commentDto) {
        Comment parent = commentRepository.findCommentWithPostById(commentDto.getParentId());

        Comment replyComment = new Comment(parent.getPost(), commentDto);
        replyComment.setParentComment(parent);

        commentRepository.save(replyComment);
        return replyComment.getId();
    }

    public List<CommentDto> findCommentDtos() {
        return commentRepository.findAllCommentDtoWithPost();
    }

    public List<CommentDto> selectedPostComments(Long postId) {
        return commentRepository.selectedPostComments(postId);
    }
}
