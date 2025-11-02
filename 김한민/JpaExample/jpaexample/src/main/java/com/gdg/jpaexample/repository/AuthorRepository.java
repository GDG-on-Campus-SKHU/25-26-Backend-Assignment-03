package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
