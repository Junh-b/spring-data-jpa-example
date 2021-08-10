package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity(name="InheritanceAuthor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Novel> novels = new HashSet<>();

    @OneToMany(mappedBy = "drawingAuthor", fetch = FetchType.LAZY)
    private Set<Webtoon> drawingWebtoons = new HashSet<>();

    @OneToMany(mappedBy = "storyAuthor", fetch = FetchType.LAZY)
    private Set<Webtoon> storyWebtoons = new HashSet<>();

    @Transient
    private List<Content> contents = new ArrayList<>();

    @PostLoad
    public void initContents(){
        Set<Content> contentSet = new HashSet<>();

        contentSet.addAll(novels);
        contentSet.addAll(drawingWebtoons);
        contentSet.addAll(storyWebtoons);

        contents.addAll(contentSet);
    }

    public static Author createAuthor(String name){
        Author author = new Author();
        author.setName(name);
        return author;
    }
}
