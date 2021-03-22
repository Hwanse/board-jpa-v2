package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practical.boardjpa.domain.Board;

@Getter
@Setter
@Builder // 롬복 Builder 는 생성자를 아래와 같이 정의했을 경우엔 AllArgsConstructor 가 필요하다
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String name;

    private Boolean isUse;

    private String writer;

    private String modifier;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.name = board.getName();
        this.isUse = board.getIsUse();
        this.writer = board.getWriter();
        this.modifier = board.getModifier();
    }
}
