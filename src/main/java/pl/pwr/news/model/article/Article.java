package pl.pwr.news.model.article;

import lombok.*;

import pl.pwr.news.model.category.Category;
import pl.pwr.news.model.report.Report;
import pl.pwr.news.model.tag.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 2/29/16.
 */
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
public class Article implements Serializable {

    private static final long serialVersionUID = 132243253243243L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "image_url")
    private String imageUrl;

    private String link;

    private boolean visible = false;

    private Long views = 0L;

    private Long likes = 0L;

    private Long dislikes = 0L;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "article")
    private Set<Report> reports = new HashSet<>();

    private Date addedDate;

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void incrementViews() {
        if (this.views == null) {
            this.views = 0L;
        }
        this.views++;
    }

    public void incrementLikes() {
        if (this.likes == null) {
            this.likes = 0L;
        }

        this.likes++;
    }

    public void incrementDislikes() {
        if (this.dislikes == null) {
            this.dislikes = 0L;
        }
        this.dislikes++;
    }
}
