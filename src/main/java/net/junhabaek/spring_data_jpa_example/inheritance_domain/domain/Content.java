package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {"id"})
@Entity
public abstract class Content {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "content", cascade = CascadeType.PERSIST)
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode episode){
        episode.setContent(this);
        episodes.add(episode);
    }
}
