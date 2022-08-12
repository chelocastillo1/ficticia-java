package com.ficticiajava.main.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Validated
public final class AuthorDto extends GenericDto {
    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    //@NotBlank
    private final String fullName;

    @PastOrPresent
    private final LocalDate createdAt;

    public AuthorDto(Long id, String firstName, String lastName, LocalDate createdAt) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
        this.createdAt = createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto entity = (AuthorDto) o;
        return Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.fullName, entity.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, createdAt, fullName);
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}