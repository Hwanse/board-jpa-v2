package practical.boardjpa.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import practical.boardjpa.domain.dto.CommentDto;
import practical.boardjpa.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/api/v1/comments")
    public ResultResponse<List<CommentDto>> comments() {
        List<CommentDto> commentDtos = commentService.findCommentDtos();
        return new ResultResponse<>(commentDtos.size(), commentDtos);
    }

    @GetMapping("/api/v2/{postId}/comments")
    public ResultResponse<List<CommentDto>> selectedPostComments(
        @PathVariable("postId") Long postId) {
        List<CommentDto> commentDtos = commentService.selectedPostComments(postId);
        return new ResultResponse<>(commentDtos.size(), commentDtos);
    }

    @Data
    @AllArgsConstructor
    static class ResultResponse<T> {
        private int count;
        private T data;
    }

}
