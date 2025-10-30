package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.daydto.DayInfoResponseDto;
import com.gdg.jpaexample.dto.daydto.DaySaveRequestDto;
import com.gdg.jpaexample.dto.itemdto.ItemInfoResponseDto;
import com.gdg.jpaexample.dto.itemdto.ItemSaveRequestDto;
import com.gdg.jpaexample.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")

public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemInfoResponseDto> saveItem(@RequestBody ItemSaveRequestDto itemSaverRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(itemSaverRequestDto));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable Long itemId) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItem(itemId));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId,
                                        @RequestBody ItemSaveRequestDto itemSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateItem(itemId, itemSaveRequestDto));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ItemInfoResponseDto> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<ItemInfoResponseDto>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItem());
    }
}
