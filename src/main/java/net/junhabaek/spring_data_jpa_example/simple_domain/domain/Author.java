package net.junhabaek.spring_data_jpa_example.simple_domain.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
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
