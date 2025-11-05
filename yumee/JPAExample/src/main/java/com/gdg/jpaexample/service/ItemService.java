package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Day;
import com.gdg.jpaexample.domain.Item;
import com.gdg.jpaexample.dto.itemdto.ItemInfoResponseDto;
import com.gdg.jpaexample.dto.itemdto.ItemSaveRequestDto;
import com.gdg.jpaexample.repository.DayRepository;
import com.gdg.jpaexample.repository.ItemRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final DayRepository dayRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private final Validator validator;

    @Transactional
    public ItemInfoResponseDto saveItem(ItemSaveRequestDto itemSaveRequestDto) {
        Day day = dayRepository.findById(itemSaveRequestDto.getDayId())
                .orElseThrow(() -> new RuntimeException("해당 날짜가 존재하지 않습니다."));

        Item item = Item.builder()
                .day(day)
                .title(itemSaveRequestDto.getTitle())
                .price(itemSaveRequestDto.getPrice())
                .amount(itemSaveRequestDto.getAmount())
                .build();
        
        itemRepository.save(item);

        return ItemInfoResponseDto.from(item);
    }

    @Transactional(readOnly = true)
    public ItemInfoResponseDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 품목을 찾을 수 없습니다."));

        return ItemInfoResponseDto.from(item);
    }

    @Transactional
    public ItemInfoResponseDto updateItem(Long itemId, ItemSaveRequestDto itemSaveRequestDto) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 품목을 찾을 수 없습니다."));

        Integer amount = itemSaveRequestDto.getAmount();
        Integer price = itemSaveRequestDto.getPrice();

        Day day = dayRepository.findById(itemSaveRequestDto.getDayId())
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜를 찾을 수 없습니다."));

        item.update(itemSaveRequestDto.getTitle(), price, amount, day);

        return ItemInfoResponseDto.from(item);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Transactional(readOnly = true)
    public List<ItemInfoResponseDto> getAllItem() {
        return itemRepository.findAll()
                .stream()
                .map(ItemInfoResponseDto::from)
                .toList();
    }
}
