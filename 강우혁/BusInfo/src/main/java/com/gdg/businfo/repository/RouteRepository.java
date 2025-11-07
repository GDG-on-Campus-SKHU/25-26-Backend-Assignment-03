package com.gdg.businfo.repository;

import com.gdg.businfo.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
