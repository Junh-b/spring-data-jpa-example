package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity(name="InheritanceAuthor")
public class Author {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

//     @OneToMany()
//     private List<Content> contents = new ArrayList<>();
}
