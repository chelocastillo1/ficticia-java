package com.ficticiajava.main.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

public final class ArticleDto extends GenericDto {

    @NotBlank
    private final String title;

    @NotBlank
    private final String description;

    @NotBlank
    private final String url;

    @NotBlank
    private final String urlToImage;

    @PastOrPresent
    private final LocalDateTime publishedAt;

    @NotBlank
    private final String content;

    @Null
    private final AuthorDto author;

    @Null
    private final SourceDto source;

    public ArticleDto(Long nId, String title, String description, String url, String urlToImage, LocalDateTime publishedAt, String content, AuthorDto author, SourceDto source) {
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

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public SourceDto getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleDto that)) return false;

        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        if (!url.equals(that.url)) return false;
        if (!urlToImage.equals(that.urlToImage)) return false;
        if (!Objects.equals(publishedAt, that.publishedAt)) return false;
        if (!content.equals(that.content)) return false;
        if (!Objects.equals(author, that.author)) return false;
        return Objects.equals(source, that.source);
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
        return "ArticleDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                '}';
    }
}