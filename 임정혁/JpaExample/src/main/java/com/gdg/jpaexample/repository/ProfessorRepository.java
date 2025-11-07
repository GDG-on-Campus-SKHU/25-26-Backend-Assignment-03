package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
    @Query("SELECT p FROM Professor p LEFT JOIN FETCH p.major WHERE p.id = :id")
    Optional<Professor> findByIdWithMajor(@Param("id") Long id);
    
    @Query("SELECT p FROM Professor p LEFT JOIN FETCH p.major")
    List<Professor> findAllWithMajor();
}
