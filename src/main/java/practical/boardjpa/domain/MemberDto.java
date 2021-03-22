package practical.boardjpa.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private Long id;

    private String name;

    private Boolean isUse;

}
