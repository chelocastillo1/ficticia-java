package com.ficticiajava.main.entity;

import com.ficticiajava.main.service.SourceService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa el origen para artículos/autores.
 **/
@Entity
@Table(name = "source")
public final class Source extends GenericEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "source", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}/*, orphanRemoval = true*/)
    private Set<Article> articles = new HashSet<>(); /* Un SOURCE tiene muchos ARTICLE */

    public Source() { // default
        super();
    }

    public Source(String name, String contenido, LocalDate createdAt) {
        super();
        this.name = name;
        this.code = name != null ? SourceService.toStringCoded(name.trim()) : null;
        this.contenido = contenido;
        this.createdAt = createdAt;
    }

    public Source(Long nId, String name, String contenido, LocalDate createdAt) {
        super(nId);
        this.name = name;
        this.code = SourceService.toStringCoded(name.trim());
        this.contenido = contenido;
        this.createdAt = createdAt;
    }

    public void removeArticle(Article n) {
        if(this.articles.contains(n)) {
            this.articles.remove(n);
            n.setSource(null);
        }
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;

        Source source = (Source) o;

        if (!name.equals(source.name)) return false;
        if (!code.equals(source.code)) return false;
        if (!contenido.equals(source.contenido)) return false;
        return createdAt.equals(source.createdAt);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + contenido.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", contenido='" + contenido + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}