package com.example.jpaexample.service;

import com.example.jpaexample.common.exception.DeleteFailureException;
import com.example.jpaexample.common.exception.NotFoundException;
import com.example.jpaexample.domain.Project;
import com.example.jpaexample.dto.project.ProjectCreateRequest;
import com.example.jpaexample.dto.project.ProjectResponse;
import com.example.jpaexample.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectResponse create(ProjectCreateRequest request) {
        if (projectRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("이미 존재하는 프로젝트명입니다: " + request.getName());
        }
        if (projectRepository.existsByKeyCode(request.getKeyCode())) {
            throw new IllegalArgumentException("이미 존재하는 프로젝트 키입니다: " + request.getKeyCode());
        }
        Project saved = projectRepository.save(Project.of(request.getName(), request.getKeyCode()));
        return ProjectResponse.from(saved);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::from)
                .toList();
    }

    public Project getEntity(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("프로젝트를 찾을 수 없습니다. id=" + id));
    }

    public ProjectResponse findById(Long id) {
        return ProjectResponse.from(getEntity(id));
    }

    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("삭제하려는 프로젝트가 존재하지 않습니다. id=" + id);
        }
        projectRepository.deleteById(id);
        if (projectRepository.existsById(id)) {
            throw new DeleteFailureException("프로젝트 삭제에 실패했습니다. id=" + id);
        }
    }
}
