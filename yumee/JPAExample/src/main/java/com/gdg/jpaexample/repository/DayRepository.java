package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
