package net.junhabaek.spring_data_jpa_example.persistence.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder @Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Article article;
}
