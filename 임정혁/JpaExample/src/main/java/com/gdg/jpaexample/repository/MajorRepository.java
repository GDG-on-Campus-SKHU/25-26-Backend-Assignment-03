package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
}
