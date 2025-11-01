package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Long> {
}
