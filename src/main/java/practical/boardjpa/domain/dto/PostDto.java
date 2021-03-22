package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practical.boardjpa.domain.Post;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String contents;

    private Boolean isUse;

    private String writer;

    private String modifier;

    private Long parentId;

    private Long boardId;

    @QueryProjection
    public PostDto(Long id, String title, String contents, Boolean isUse, String writer,
                   String modifier, Long parentId, Long boardId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.isUse = isUse;
        this.writer = writer;
        this.modifier = modifier;
        this.parentId = parentId;
        this.boardId = boardId;
    }

}
