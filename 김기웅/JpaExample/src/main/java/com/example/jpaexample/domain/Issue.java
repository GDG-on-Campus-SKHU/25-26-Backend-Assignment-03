package com.example.jpaexample.domain;

import jakarta.persistence.*;
import java.util.Objects;

import static com.example.jpaexample.domain.IssueEnums.Priority;
import static com.example.jpaexample.domain.IssueEnums.Status;

@Entity
@Table(name = "issues")
public class Issue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 140)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    protected Issue() { }

    private Issue(String title, String description, Status status, Priority priority) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public static Issue of(String title, String description, Status status, Priority priority) {
        return new Issue(title, description, status, priority);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public Project getProject() { return project; }


    void setProjectInternal(Project project) { this.project = project; }

    public void change(String newTitle, String newDescription, Status newStatus, Priority newPriority) {
        if (newTitle == null || newTitle.isBlank()) { throw new IllegalArgumentException("title must not be blank"); }
        this.title = newTitle;
        this.description = newDescription;
        this.status = newStatus;
        this.priority = newPriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() { return 31; }
}
