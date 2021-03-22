package practical.boardjpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.dto.BoardDto;

@Profile("test")
@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시판을 생성한다")
    public void create_board() throws Exception {
        // given
        BoardDto boardDto = createBoardDto();

        // when
        Long boardId = boardService.createBoard(boardDto);
        em.flush();
        em.clear();

        Board findBoard = boardService.findBoardById(boardId);

        // then
        assertThat(findBoard).isNotNull();
        assertThat(findBoard.getId()).isEqualTo(boardId);
    }


    @Test
    @DisplayName("게시판 전체를 조회한다")
    public void select_all_board() throws Exception {
        // given
        BoardDto boardDto = createBoardDto();
        boardService.createBoard(boardDto);

        em.flush();
        em.clear();

        // when
        List<BoardDto> allBoardDtos = boardService.findAllBoardDtos();

        // then
        assertThat(allBoardDtos).isNotEmpty();
    }

    @Test
    @DisplayName("게시판을 수정한다")
    public void modify_board() throws Exception {
        // given
        BoardDto boardDto = createBoardDto();
        Long boardId = boardService.createBoard(boardDto);

        boardDto.setId(boardId);
        boardDto.setIsUse(false);
        boardDto.setName("수정된 게시판");
        boardDto.setModifier("수정자1");

        // when
        boardService.modifyBoard(boardDto);
        em.flush();
        em.clear();

        Board findBoard = boardService.findBoardById(boardId);
        // then
        assertThat(findBoard.getName()).isEqualTo("수정된 게시판");
        assertThat(findBoard.getModifier()).isEqualTo("수정자1");
        assertThat(findBoard.getIsUse()).isFalse();

    }


    private BoardDto createBoardDto() {
        return BoardDto.builder()
            .name("게시판1")
            .isUse(Boolean.TRUE)
            .writer("test")
            .modifier("test").build();
    }

}