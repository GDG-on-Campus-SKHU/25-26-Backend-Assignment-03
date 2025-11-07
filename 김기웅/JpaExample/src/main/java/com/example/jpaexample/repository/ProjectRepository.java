package com.example.jpaexample.repository;


import com.example.jpaexample.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    boolean existsByKeyCode(String keyCode);
}
