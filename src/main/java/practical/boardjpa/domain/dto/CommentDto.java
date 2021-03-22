package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private String contents;

    private Long postId;

    private String writer;

    private String modifier;

    private Boolean isUse;

    private Long parentId;

}
