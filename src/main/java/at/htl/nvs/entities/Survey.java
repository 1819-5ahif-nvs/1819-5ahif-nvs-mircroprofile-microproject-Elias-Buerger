package at.htl.nvs.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY")
//@NamedQuery(name = "Survey.fromUser", query = "select survey from Survey survey where survey.creator.id = ?1")
public class Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@JoinColumn(name = "CREATOR")
    @ManyToOne
    @Valid
    @NotNull
    private User creator;*/

    @Column(name = "TIME_OF_CREATION")
    @NotNull
    private LocalDateTime timeOfCreation;

    @Column(name = "TITLE")
    @Size(min = 3, max = 35, message = "Title must be between 3 and 35 characters")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Title can only contain letters and numbers")
    private String title;

    @Column(name = "DESCRIPTION")
    @Size(min = 0, max = 128, message = "Description must be shorter than 128 characters")
    private String description;

    public Survey() {
    }

    public Survey(LocalDateTime timeOfCreation, String title, String description) {
        this.timeOfCreation = timeOfCreation;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
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

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Survey && ((Survey)obj).id == id);
    }
}