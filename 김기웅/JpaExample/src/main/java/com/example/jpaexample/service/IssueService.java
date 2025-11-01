package com.example.jpaexample.service;

import com.example.jpaexample.common.exception.DeleteFailureException;
import com.example.jpaexample.common.exception.NotFoundException;
import com.example.jpaexample.domain.Issue;
import com.example.jpaexample.domain.Project;
import com.example.jpaexample.dto.issue.IssueCreateRequest;
import com.example.jpaexample.dto.issue.IssueResponse;
import com.example.jpaexample.dto.issue.IssueUpdateRequest;
import com.example.jpaexample.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssueService {

    private final IssueRepository issueRepository;
    private final ProjectService projectService;

    public IssueService(IssueRepository issueRepository, ProjectService projectService) {
        this.issueRepository = issueRepository;
        this.projectService = projectService;
    }

    public IssueResponse create(IssueCreateRequest request) {
        Project project = projectService.getEntity(request.getProjectId());
        Issue issue = Issue.of(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getPriority()
        );
        project.addIssue(issue);
        Issue saved = issueRepository.save(issue);
        return IssueResponse.from(saved);
    }

    public IssueResponse findById(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("이슈를 찾을 수 없습니다. id=" + id));
        return IssueResponse.from(issue);
    }

    public List<IssueResponse> findAll() {
        return issueRepository.findAll().stream()
                .map(IssueResponse::from)
                .toList();
    }

    public IssueResponse update(Long id, IssueUpdateRequest request) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("이슈를 찾을 수 없습니다. id=" + id));
        issue.change(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getPriority()
        );
        return IssueResponse.from(issue);
    }

    public void delete(Long id) {
        Optional<Issue> optional = issueRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("삭제하려는 이슈가 존재하지 않습니다. id=" + id);
        }
        issueRepository.deleteById(id);
        if (issueRepository.existsById(id)) {
            throw new DeleteFailureException("이슈 삭제에 실패했습니다. id=" + id);
        }
    }
}
