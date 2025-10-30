package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
