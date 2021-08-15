package net.junhabaek.spring_data_jpa_example.persistence.domain;

import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Singular
    @OneToMany(mappedBy = "article")
//    @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL})
    private final List<Comment> comments = new ArrayList<>();

    public void changeContent(String content){
        this.content = content;
    }

    public void destory(){
        comments.forEach((comment)->comment.setArticle(null));
    }
}
