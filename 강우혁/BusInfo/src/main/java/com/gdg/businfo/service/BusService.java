package com.gdg.businfo.service;

import com.gdg.businfo.domain.Bus;
import com.gdg.businfo.domain.Company;
import com.gdg.businfo.domain.Route;
import com.gdg.businfo.dto.BusInfoResponseDto;
import com.gdg.businfo.dto.BusSaveRequestDto;
import com.gdg.businfo.dto.CompanySaveRequestDto;
import com.gdg.businfo.dto.RouteSaveRequestDto;
import com.gdg.businfo.repository.BusRepository;
import com.gdg.businfo.repository.CompanyRepository;
import com.gdg.businfo.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final CompanyRepository companyRepository;

    //create
    @Transactional
    public BusInfoResponseDto saveBus(BusSaveRequestDto busSaveRequestDto) {
        Company company = companyRepository.findById(busSaveRequestDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("회사를 찾을 수 없습니다."));
        Route route = routeRepository.findById(busSaveRequestDto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("노선 정보가 없습니다."));
        Bus bus = Bus.builder()
                .busNumber(busSaveRequestDto.getBusNumber())
                .type(busSaveRequestDto.getType())
                .company(company)
                .route(route)
                .build();
        busRepository.save(bus);
        return BusInfoResponseDto.from(bus);
    }
    //read
    @Transactional(readOnly = true)
    public BusInfoResponseDto getBus(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("버스 정보가 없습니다."))
        );
        return BusInfoResponseDto.from(bus);
    }
    @Transactional(readOnly = true)
    public List<BusInfoResponseDto> getAllBus() {
            return busRepository.findAll()
                    .stream()
                    .map(BusInfoResponseDto::from)
                    .toList();
    }
    //update
    @Transactional
    public BusInfoResponseDto updateBus(Long id, BusSaveRequestDto busSaveRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("버스 아이디를 확인 할 수 없습니다.")
        );
        bus.update(busSaveRequestDto.getBusNumber(),busSaveRequestDto.getType());
        return BusInfoResponseDto.from(bus);
    }
    @Transactional
    public BusInfoResponseDto updateCompanyBus(Long id, Long companyId, BusSaveRequestDto busSaveRequestDto, CompanySaveRequestDto companySaveRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("버스를 찾을 수 없습니다.")
        );
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new IllegalArgumentException(("회사를 찾을 수 없습니다."))
        );
        company.update(companySaveRequestDto.getCompanyName(),companySaveRequestDto.getPhoneNumber(),companySaveRequestDto.getRegion());
        bus.updateCompanyBus(busSaveRequestDto.getBusNumber(),busSaveRequestDto.getType(), company);
        return BusInfoResponseDto.from(bus);
    }
    @Transactional
    public BusInfoResponseDto updateRouteBus(Long id, Long routeId, BusSaveRequestDto busSaveRequestDto, RouteSaveRequestDto routeSaveRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("버스를 찾을 수 없습니다.")
        );
        Route route = routeRepository.findById(routeId).orElseThrow(
                () -> new IllegalArgumentException("노선을 찾을 수 없습니다.")
        );
        route.update(routeSaveRequestDto.getRouteName(),routeSaveRequestDto.getStartPoint(),routeSaveRequestDto.getEndPoint());
        bus.updateRouteBus(busSaveRequestDto.getBusNumber(), busSaveRequestDto.getType(), route);
        return BusInfoResponseDto.from(bus);
    }
    @Transactional
    public BusInfoResponseDto updateAllBus(Long id, Long companyId, Long routeId, BusSaveRequestDto busSaveRequestDto,
                                           CompanySaveRequestDto companySaveRequestDto, RouteSaveRequestDto routeSaveRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("버스를 찾을 수 없습니다.")
        );
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new IllegalArgumentException("회사를 찾을 수 없습니다.")
        );
        Route route = routeRepository.findById(routeId).orElseThrow(
                () -> new IllegalArgumentException("노선을 찾을 수 없습니다.")
        );
        company.update(companySaveRequestDto.getCompanyName(),companySaveRequestDto.getPhoneNumber(),companySaveRequestDto.getRegion());
        route.update(routeSaveRequestDto.getRouteName(),routeSaveRequestDto.getStartPoint(),routeSaveRequestDto.getEndPoint());
        bus.updateAllBus(busSaveRequestDto.getBusNumber(), busSaveRequestDto.getType(), company, route);
        return BusInfoResponseDto.from(bus);
    }
    //delete
    @Transactional
    public void deleteBus(Long id) {
        busRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("버스를 확인 할 수 없습니다.")
        );
        busRepository.deleteById(id);
    }
}
