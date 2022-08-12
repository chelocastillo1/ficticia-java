package com.ficticiajava.main.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa el autor de un artículo publicado.
 **/
@Entity
@Table(name = "author")
public final class Author extends GenericEntity {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Formula(value = "CONCAT(first_name, ' ', last_name)")
    @Column(name = "full_name", unique = true, length = 101)
    private String fullName; /* Atributo calculado, no tiene sentido almacenarlo en la base de datos. */

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}/*, orphanRemoval = true*/)
    private Set<Article> articles = new HashSet<>(); /* Un AUTHOR tiene muchos ARTICLE */

    public Author() { // default
        super();
    }

    public Author(String firstName, String lastName, LocalDate createdAt) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
        this.createdAt = createdAt;
    }

    public Author(Long nId, String firstName, String lastName, LocalDate createdAt) {
        super(nId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
        this.createdAt = createdAt;
    }

    public void removeArticle(Article n) {
        if(this.articles.contains(n)) {
            this.articles.remove(n);
            n.setAuthor(null);
        }
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;

        if (!firstName.equals(author.firstName)) return false;
        if (!lastName.equals(author.lastName)) return false;
        if (!fullName.equals(author.fullName)) return false;
        return createdAt.equals(author.createdAt);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}