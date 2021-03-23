package practical.boardjpa.api;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.Comment;
import practical.boardjpa.domain.Post;
import practical.boardjpa.domain.dto.BoardDto;
import practical.boardjpa.domain.dto.CommentDto;
import practical.boardjpa.domain.dto.PostDto;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class InitComment {

    private final InitCommentService initCommentService;

    @PostConstruct
    public void init() {
        initCommentService.init();
    }

    @Component
    static class InitCommentService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            BoardDto boardDto = BoardDto.builder()
                                        .name("게시판")
                                        .writer("admin")
                                        .modifier("admin")
                                        .isUse(Boolean.TRUE)
                                        .build();
            Board board = new Board(boardDto);

            em.persist(board);

            PostDto postDtoA = PostDto.builder()
                                     .boardId(board.getId())
                                     .title("게시글A")
                                     .contents("content")
                                     .isUse(Boolean.TRUE)
                                     .writer("test")
                                     .modifier("test")
                                     .build();
            Post postA = new Post(board, postDtoA);

            PostDto postDtoB = PostDto.builder()
                                     .boardId(board.getId())
                                     .title("게시글B")
                                     .contents("content")
                                     .isUse(Boolean.TRUE)
                                     .writer("test")
                                     .modifier("test")
                                     .build();
            Post postB = new Post(board, postDtoB);

            em.persist(postA);
            em.persist(postB);

            for (int i = 0; i < 100 ; i++) {
                CommentDto commentDto = CommentDto.builder()
                                             .writer("test" + (i + 1))
                                             .modifier("test" + (i + 1))
                                             .isUse(Boolean.TRUE)
                                             .contents("내용" + (i + 1))
                                             .build();
                Post selectedPost = i % 2 == 0 ? postA : postB;
                Comment comment = new Comment(selectedPost, commentDto);
                em.persist(comment);
            }
        }

    }

}
