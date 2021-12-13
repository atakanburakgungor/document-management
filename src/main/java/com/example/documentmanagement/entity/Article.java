package com.example.documentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Article extends TrackableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    @SequenceGenerator(name = "article_generator", sequenceName = "article_seq")
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    private String title;

    private String author;

    private String articleContent;

    @CreatedDate
    private LocalDateTime publishDate;

    private int starCount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = Review.class)
    private List<Review> reviews = new LinkedList<>();

    public void assignParentToChilds() {
        if (getReviews() != null) {
            for (Review entity : getReviews()) {
                entity.setArticle(this);
            }
        }
    }

}
