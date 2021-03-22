package practical.boardjpa.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private Long id;

    private String name;

    private Boolean isUse;

    private String writer;

    private String modifier;

}
