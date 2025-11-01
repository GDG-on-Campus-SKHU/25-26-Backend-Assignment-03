package com.example.jpaexample.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80, unique = true)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String keyCode;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Issue> issues = new ArrayList<>();

    protected Project() { }

    private Project(String name, String keyCode) {
        this.name = name;
        this.keyCode = keyCode;
        this.createdAt = LocalDateTime.now();
    }

    public static Project of(String name, String keyCode) {
        return new Project(name, keyCode);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getKeyCode() { return keyCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Issue> getIssues() { return List.copyOf(issues); }

    public void addIssue(Issue issue) {
        if (issue == null) { throw new IllegalArgumentException("issue must not be null"); }
        issues.add(issue);
        issue.setProjectInternal(this);
    }

    public void removeIssue(Issue issue) {
        if (issue == null) { return; }
        if (issues.remove(issue)) {
            issue.setProjectInternal(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() { return 31; }
}
