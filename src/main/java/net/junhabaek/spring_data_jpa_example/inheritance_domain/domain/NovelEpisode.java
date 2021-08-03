package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter @Setter
@Entity
public class NovelEpisode extends Episode{
    @Column(name="novel_text", nullable = false)
    private String text;
}
