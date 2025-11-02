package com.example.jpaexample.repository;

import com.example.jpaexample.domain.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}
