package com.example.jpaexample.dto.project;

import com.example.jpaexample.domain.Project;
import java.time.LocalDateTime;

public final class ProjectResponse {

    private final Long id;
    private final String name;
    private final String keyCode;
    private final LocalDateTime createdAt;

    public ProjectResponse(Long id, String name, String keyCode, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.keyCode = keyCode;
        this.createdAt = createdAt;
    }

    public static ProjectResponse from(Project p) {
        return new ProjectResponse(p.getId(), p.getName(), p.getKeyCode(), p.getCreatedAt());
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getKeyCode() { return keyCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
