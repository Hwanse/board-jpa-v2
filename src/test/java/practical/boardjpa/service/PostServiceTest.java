package practical.boardjpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.Post;
import practical.boardjpa.domain.dto.BoardDto;
import practical.boardjpa.domain.dto.PostDto;
import practical.boardjpa.repository.BoardRepository;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private PostService postService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글을 생성한다")
    public void create_post() throws Exception {
        // given
        BoardDto boardDto = createBoardDto();
        Board board = new Board(boardDto);
        boardRepository.save(board);

        PostDto postDto = createPostDto(board);

        // whens
        Long savedId = postService.createPost(postDto);
        em.flush();
        em.clear();

        // then
        Post findPost = postService.findPostById(savedId);
        assertThat(findPost.getId()).isEqualTo(savedId);
        assertThat(findPost.getBoard().getId()).isEqualTo(board.getId());
        assertThat(findPost.getIsUse()).isTrue();
        assertThat(findPost.getCreateDate()).isNotNull();
        assertThat(findPost.getUpdateDate()).isNotNull();
    }

    @Test
    @DisplayName("게시글을 수정한다")
    public void modify_post() throws Exception {
        // given

        // when

        // then

    }

    @Test
    @DisplayName("답변 게시글을 생성한다")
    public void create_reply_post() throws Exception {
        // given
        BoardDto boardDto = createBoardDto();
        Board board = new Board(boardDto);
        boardRepository.save(board);

        PostDto postDto = createPostDto(board);
        Long savedId = postService.createPost(postDto);

        Post parent = postService.findPostById(savedId);
        PostDto replyPostDto = createPostDto(board, parent);

        // when
        Long savedReplyId = postService.createReplyPost(replyPostDto);
        em.flush();
        em.clear();

        // then
        Post findParent = postService.findPostById(savedId);
        Post findReplyPost = postService.findPostById(savedReplyId);
        assertThat(findReplyPost.getParent()).isEqualTo(findParent);
        assertThat(findReplyPost.getIsUse()).isTrue();

    }

    private BoardDto createBoardDto() {
        return BoardDto.builder()
                       .name("게시판1")
                       .isUse(Boolean.TRUE)
                       .writer("test")
                       .modifier("test").build();
    }

    private PostDto createPostDto(Board board) {
        return PostDto
            .builder()
            .isUse(Boolean.TRUE)
            .writer("test")
            .modifier("test")
            .boardId(board.getId())
            .title("제목")
            .contents("내용")
            .build();
    }

    private PostDto createPostDto(Board board, Post post) {
        return PostDto
            .builder()
            .isUse(Boolean.TRUE)
            .writer("test")
            .modifier("test")
            .boardId(board.getId())
            .title("제목")
            .contents("내용")
            .parentId(post.getId())
            .build();
    }

}