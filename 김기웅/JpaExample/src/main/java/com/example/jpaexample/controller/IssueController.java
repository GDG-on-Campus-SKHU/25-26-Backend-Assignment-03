package com.example.jpaexample.controller;

import com.example.jpaexample.dto.issue.IssueCreateRequest;
import com.example.jpaexample.dto.issue.IssueResponse;
import com.example.jpaexample.dto.issue.IssueUpdateRequest;
import com.example.jpaexample.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<IssueResponse> create(@Valid @RequestBody IssueCreateRequest request) {
        IssueResponse response = issueService.create(request);
        return ResponseEntity.created(URI.create("/api/issues/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<IssueResponse>> list() {
        return ResponseEntity.ok(issueService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IssueResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody IssueUpdateRequest request) {
        return ResponseEntity.ok(issueService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
