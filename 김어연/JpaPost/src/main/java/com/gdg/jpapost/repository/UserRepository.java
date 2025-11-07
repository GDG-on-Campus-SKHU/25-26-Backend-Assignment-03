package com.gdg.jpapost.repository;

import com.gdg.jpapost.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}