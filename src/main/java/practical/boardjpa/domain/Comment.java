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
import practical.boardjpa.domain.dto.CommentDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(fetch = LAZY, mappedBy = "parent",
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replyComments = new ArrayList<>();

    public Comment(Post post, CommentDto commentDto) {
        this.contents = contents;
        this.post = post;
        post.getComments().add(this);
    }

    public void addReplyComment(Comment parent) {
        this.parent = parent;
        parent.getReplyComments().add(this);
    }

    public void modifyComment(CommentDto commentDto) {
        super.setIsUse(commentDto.getIsUse());
        super.setWriter(commentDto.getWriter());
        super.setModifier(commentDto.getModifier());
        this.contents = commentDto.getContents();
    }
}