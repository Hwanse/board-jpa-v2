package practical.boardjpa.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practical.boardjpa.api.request.PostSearchRequest;
import practical.boardjpa.domain.Board;
import practical.boardjpa.domain.Post;
import practical.boardjpa.domain.dto.PostDto;
import practical.boardjpa.repository.BoardRepository;
import practical.boardjpa.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public Long createPost(PostDto postDto) {
        Board findBoard = boardRepository.findById(postDto.getBoardId())
                                         .orElseThrow(IllegalAccessError::new);
        Post post = new Post(findBoard, postDto);

        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long createReplyPost(PostDto postDto) {
        Board findBoard = boardRepository.findById(postDto.getBoardId())
                                         .orElseThrow(IllegalAccessError::new);

        Post parent = postRepository.findById(postDto.getParentId())
                                    .orElseThrow(IllegalAccessError::new);

        Post post = new Post(findBoard, postDto);
        post.addReplyPost(parent);

        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void modifyPost(PostDto postDto) {
        Post findPost = postRepository.findById(postDto.getId())
                                      .orElseThrow(IllegalAccessError::new);
        findPost.modifyPost(postDto);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
    }

    public Page<PostDto> searchPostPage(PostSearchRequest search, Pageable pageable) {
        return postRepository.searchPostPage(search, pageable);
    }

}
