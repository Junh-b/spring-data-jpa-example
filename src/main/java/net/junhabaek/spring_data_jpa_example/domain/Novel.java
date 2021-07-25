package net.junhabaek.spring_data_jpa_example.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
public class Novel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;
}
