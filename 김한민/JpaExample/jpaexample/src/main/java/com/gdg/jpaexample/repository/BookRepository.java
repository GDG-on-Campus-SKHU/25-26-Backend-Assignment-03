package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
