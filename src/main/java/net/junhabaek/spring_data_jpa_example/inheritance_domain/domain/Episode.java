package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Episode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="content_id", nullable = false)
    private Content content;
}
