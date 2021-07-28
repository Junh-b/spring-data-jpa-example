package net.junhabaek.spring_data_jpa_example.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of={"id"})
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Novel> novels =new ArrayList<>();

    public static Author with(String name){
        Author author = new Author();
        author.name= name;
        return author;
    }

    public void addNovel(Novel novel){
        this.novels.add(novel);
    }
}
