package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    private String contents;

    private Long postId;

    private String writer;

    private String modifier;

    private Boolean isUse;

    private Long parentId;

    private int level;

    @QueryProjection
    public CommentDto(Long id, String contents, Long postId, String writer, String modifier,
                      Boolean isUse, Long parentId, int level) {
        this.id = id;
        this.contents = contents;
        this.postId = postId;
        this.writer = writer;
        this.modifier = modifier;
        this.isUse = isUse;
        this.parentId = parentId;
        this.level = level;
    }
}
