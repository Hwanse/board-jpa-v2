package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long id;

    private String name;

    private String contents;

    private Boolean isUse;

    private String writer;

    private String modifier;

    private Long parentId;

}
