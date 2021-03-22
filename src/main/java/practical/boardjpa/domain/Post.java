package practical.boardjpa.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practical.boardjpa.domain.common.CommonEntity;
import practical.boardjpa.domain.dto.PostDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends CommonEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "title")
    private String title;

    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Post parent;

    @OneToMany(fetch = LAZY, mappedBy = "parent",
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> replyPosts = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "post",
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(Board board, PostDto postDto) {
        super.setIsUse(postDto.getIsUse());
        super.setWriter(postDto.getWriter());
        super.setModifier(postDto.getModifier());
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
        this.board = board;
        board.getPosts().add(this);
    }

    public void addReplyPost(Post parent) {
        this.parent = parent;
        parent.getReplyPosts().add(this);
    }

    public void modifyPost(PostDto postDto) {
        super.setIsUse(postDto.getIsUse());
        super.setWriter(postDto.getWriter());
        super.setModifier(postDto.getModifier());
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
    }

}