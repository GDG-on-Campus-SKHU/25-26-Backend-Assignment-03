package com.gdg.businfo.controller;

import com.gdg.businfo.dto.*;
import com.gdg.businfo.repository.RouteRepository;
import com.gdg.businfo.service.BusService;
import com.gdg.businfo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {
    private final BusService busService;
    private final CompanyService companyService;
    private final RouteRepository routeRepository;

    @PostMapping
    public ResponseEntity<BusInfoResponseDto> saveBus(@RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.saveBus(busSaveRequestDto));
    }

    @GetMapping("/{busId}")
    public ResponseEntity<BusInfoResponseDto> getBus(@PathVariable Long busId) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.getBus(busId));
    }

    @GetMapping("/busAll")
    public ResponseEntity<List<BusInfoResponseDto>> getAllBus() {
        return ResponseEntity.status(HttpStatus.OK).body(busService.getAllBus());
    }
    @PatchMapping("/{busId}")
    public ResponseEntity<BusInfoResponseDto> updateBus(@PathVariable Long busId,
                                                        @RequestBody BusSaveRequestDto busSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateBus(busId, busSaveRequestDto));
    }
    @PatchMapping("/{busId}/company/{companyId}")
    public ResponseEntity<BusInfoResponseDto> updateCompanyBus(@PathVariable Long busId,
                                                                                       @PathVariable Long companyId,
                                                                                       @RequestBody BusSaveRequestDto busSaveRequestDto,
                                                               @RequestBody CompanySaveRequestDto companySaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateCompanyBus(busId, companyId, busSaveRequestDto, companySaveRequestDto));
    }
    @PatchMapping("/{busId}/route/{routeId}")
    public ResponseEntity<BusInfoResponseDto> updateRouteBus(@PathVariable Long busId,
                                                             @PathVariable Long routeId,
                                                             @RequestBody BusSaveRequestDto busSaveRequestDto,
                                            @RequestBody RouteSaveRequestDto routeSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateRouteBus(busId, routeId, busSaveRequestDto, routeSaveRequestDto));
    }
    @PatchMapping("/{busId}/company/{companyId}/route/{routeId}")
    public ResponseEntity<BusInfoResponseDto> updateAllBus(@PathVariable Long busId,
                                          @PathVariable Long companyId,
                                          @PathVariable Long routeId,
                                          @RequestBody BusSaveRequestDto busSaveRequestDto,
                                                           @RequestBody CompanySaveRequestDto companySaveRequestDto,
                                                           @RequestBody RouteSaveRequestDto routeSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(busService.updateAllBus(busId, companyId, routeId, busSaveRequestDto,
                companySaveRequestDto, routeSaveRequestDto));
    }
    @DeleteMapping("/{busId}")
    public ResponseEntity<BusInfoResponseDto> deleteBus(@PathVariable Long busId) {
        busService.deleteBus(busId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/company/{companyId}")
    public ResponseEntity<BusInfoResponseDto> deleteCompanyBus(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/route/{routeId}")
    public ResponseEntity<BusInfoResponseDto> deleteRouteBus(@PathVariable Long routeId) {
        routeRepository.deleteById(routeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
