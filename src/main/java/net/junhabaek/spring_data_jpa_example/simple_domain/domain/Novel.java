package net.junhabaek.spring_data_jpa_example.simple_domain.domain;

import javax.persistence.*;

@Entity
public class Novel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(name="FK_novel_author"))
    private Author author;

    @Column(nullable = false)
    String name;

    public String getName() {
        return name;
    }

}
