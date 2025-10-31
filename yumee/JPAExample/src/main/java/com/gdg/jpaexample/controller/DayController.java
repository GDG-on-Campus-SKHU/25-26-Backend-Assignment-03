package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.daydto.DayInfoResponseDto;
import com.gdg.jpaexample.dto.daydto.DaySaveRequestDto;
import com.gdg.jpaexample.service.DayService;
import com.gdg.jpaexample.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/days")

public class DayController {

    private final DayService dayService;
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<DayInfoResponseDto> saveDay(@RequestBody DaySaveRequestDto daySaverRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dayService.saveDay(daySaverRequestDto));
    }

    @DeleteMapping("/{dayId}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long dayId) {
        dayService.deleteDay(dayId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<DayInfoResponseDto>> getAllDay() {
        return ResponseEntity.status(HttpStatus.OK).body(dayService.getAllDay());
    }
}
