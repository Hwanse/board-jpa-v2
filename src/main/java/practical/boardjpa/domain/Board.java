package practical.boardjpa.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practical.boardjpa.domain.common.CommonEntity;
import practical.boardjpa.domain.dto.BoardDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends CommonEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_name")
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public Board(BoardDto boardDto) {
        super.setWriter(boardDto.getWriter());
        super.setModifier(boardDto.getModifier());
        this.name = name;
    }

    public void modifyBoard(BoardDto boardDto) {
        super.setIsUse(boardDto.getIsUse());
        super.setWriter(boardDto.getWriter());
        super.setModifier(boardDto.getModifier());
        this.name = boardDto.getName();
    }

}
