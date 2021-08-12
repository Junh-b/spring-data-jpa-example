package net.junhabaek.spring_data_jpa_example.persistence.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Singular
    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();

    public void changeContent(String content){
        this.content = content;
    }
}
