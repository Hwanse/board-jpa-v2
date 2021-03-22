package practical.boardjpa.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practical.boardjpa.api.request.PostSearchRequest;
import practical.boardjpa.domain.dto.PostDto;

public interface PostRepositoryQuery {

    Page<PostDto> searchPostPage(PostSearchRequest search, Pageable pageable);

}
