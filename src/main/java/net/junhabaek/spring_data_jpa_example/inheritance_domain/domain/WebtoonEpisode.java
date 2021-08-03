package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter @Setter
@Entity
public class WebtoonEpisode extends Episode{
    @Column(nullable = false)
    private String imageURL;
}
