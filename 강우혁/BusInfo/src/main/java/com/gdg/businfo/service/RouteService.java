package com.gdg.businfo.service;

import com.gdg.businfo.domain.Route;
import com.gdg.businfo.dto.RouteInfoResponseDto;
import com.gdg.businfo.dto.RouteSaveRequestDto;
import com.gdg.businfo.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    //create
    @Transactional
    public RouteInfoResponseDto saveRoute(RouteSaveRequestDto routeSaveRequestDto) {
        Route route = Route.builder()
                .routeName(routeSaveRequestDto.getRouteName())
                .startPoint(routeSaveRequestDto.getStartPoint())
                .endPoint(routeSaveRequestDto.getEndPoint())
                .build();
        routeRepository.save(route);
        return RouteInfoResponseDto.from(route);
    }
    //delete
    @Transactional
    public void deleteRoute(Long id) {
        routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("노선을 찾을 수 없습니다."));
        routeRepository.deleteById(id);
    }
}
