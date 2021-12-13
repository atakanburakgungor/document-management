package com.example.documentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class Review extends TrackableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name = "review_generator", sequenceName = "review_seq")
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    private String reviewer;

    private String reviewContent;

    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_review_article"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Article article;
}
