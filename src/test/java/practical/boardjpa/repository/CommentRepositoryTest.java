package practical.boardjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.Comment;
import practical.boardjpa.domain.Post;
import practical.boardjpa.domain.dto.BoardDto;
import practical.boardjpa.domain.dto.CommentDto;
import practical.boardjpa.domain.dto.PostDto;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("댓글을 게시글 정보와 함께 조회")
    public void find_comment_with_post() throws Exception {
        // given
        Post findPost = postRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        CommentDto commentDto = CommentDto.builder()
                                     .contents("내용")
                                     .isUse(Boolean.TRUE)
                                     .writer("test")
                                     .modifier("test")
                                     .build();

        Comment comment = new Comment(findPost, commentDto);
        commentRepository.save(comment);

        em.flush();
        em.clear();

        // when
        Comment findComment = commentRepository.findCommentWithPostById(comment.getId());

        // then
        assertThat(findComment).isNotNull();
        assertThat(findComment.getPost().getId()).isEqualTo(findPost.getId());
        assertThat(findComment.getIsUse()).isTrue();
        assertThat(findComment.getCreateDate()).isNotNull();
        assertThat(findComment.getUpdateDate()).isNotNull();
    }

    @BeforeEach
    public void setup() {
        BoardDto boardDto = BoardDto.builder()
            .name("게시판")
            .writer("admin")
            .modifier("admin")
            .isUse(Boolean.TRUE)
            .build();
        Board board = new Board(boardDto);

        em.persist(board);

        PostDto postDto = PostDto.builder()
            .boardId(board.getId())
            .title("title")
            .contents("content")
            .isUse(Boolean.TRUE)
            .writer("test")
            .modifier("test")
            .build();
        Post post = new Post(board, postDto);

        em.persist(post);

        em.flush();
        em.clear();
    }

}