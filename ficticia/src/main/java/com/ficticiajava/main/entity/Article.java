package com.ficticiajava.main.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa la noticia (articulo).
 **/
@Entity
@Table(name = "article")
public final class Article extends GenericEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "url_to_image", nullable = false)
    private String urlToImage;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "content", columnDefinition = "TEXT") // , length = 3000
    private String content;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author; /* Un ARTICLE tiene un solo AUTHOR */

    @ManyToOne()
    @JoinColumn(name = "source_id")
    private Source source; /* Un ARTICLE pertenece a un solo SOURCE */

    public Article() { // default
        super();
    }

    public Article(String title, String description, String url, String urlToImage, LocalDateTime publishedAt, String content, Author author, Source source) {
        super();
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.author = author;
        this.source = source;
    }

    public Article(Long nId, String title, String description, String url, String urlToImage, LocalDateTime publishedAt, String content, Author author, Source source) {
        super(nId);
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.author = author;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!title.equals(article.title)) return false;
        if (!description.equals(article.description)) return false;
        if (!url.equals(article.url)) return false;
        if (!urlToImage.equals(article.urlToImage)) return false;
        if (!Objects.equals(publishedAt, article.publishedAt)) return false;
        if (!content.equals(article.content)) return false;
        if (!Objects.equals(author, article.author)) return false;
        return Objects.equals(source, article.source);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + urlToImage.hashCode();
        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
        result = 31 * result + content.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}