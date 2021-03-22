package practical.boardjpa.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.dto.BoardDto;
import practical.boardjpa.repository.BoardRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(BoardDto boardDto) {
        Board board = new Board(boardDto);
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }

    public void modifyBoard(BoardDto boardDto) {
        Board findBoard = boardRepository.findById(boardDto.getId())
                                         .orElseThrow(IllegalAccessError::new);
        findBoard.modifyBoard(boardDto);
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(IllegalAccessError::new);
    }

    public List<BoardDto> findAllBoardDtos() {
        return boardRepository.findAll().stream()
                       .map(BoardDto::new)
                       .collect(Collectors.toList());
    }
}
