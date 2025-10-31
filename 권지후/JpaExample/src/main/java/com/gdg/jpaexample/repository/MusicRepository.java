package com.gdg.jpaexample.repository;

import com.gdg.jpaexample.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
