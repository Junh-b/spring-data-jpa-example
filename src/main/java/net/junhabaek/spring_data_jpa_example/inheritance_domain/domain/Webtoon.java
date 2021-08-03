package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
@Entity
public class Webtoon extends Content{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="story_author_id", nullable = false)
    private Author storyAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drawing_author_id", nullable = false)
    private Author drawingAuthor;
}
