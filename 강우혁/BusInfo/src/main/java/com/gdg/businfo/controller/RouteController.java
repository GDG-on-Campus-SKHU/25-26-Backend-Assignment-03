package com.gdg.businfo.controller;

import com.gdg.businfo.dto.RouteInfoResponseDto;
import com.gdg.businfo.dto.RouteSaveRequestDto;
import com.gdg.businfo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteInfoResponseDto> saveRoute(@RequestBody  RouteSaveRequestDto routeSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.saveRoute(routeSaveRequestDto));
    }
    @DeleteMapping("/{routeId}")
    public ResponseEntity<RouteInfoResponseDto> deleteRoute(@PathVariable Long routeId) {
        routeService.deleteRoute(routeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
