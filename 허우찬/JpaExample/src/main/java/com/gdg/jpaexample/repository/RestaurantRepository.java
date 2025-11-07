package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
