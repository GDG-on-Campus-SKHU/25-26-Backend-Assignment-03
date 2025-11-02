package com.gdg.jpa.service;

import com.gdg.jpa.domain.Soon;
import com.gdg.jpa.domain.ToDo;
import com.gdg.jpa.dto.ToDoInfoResponseDto;
import com.gdg.jpa.dto.ToDoSaveRequestDto;
import com.gdg.jpa.repository.SoonRepository;
import com.gdg.jpa.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository todoRepository;
    private final SoonRepository soonRepository;

    @Transactional
    public ToDoInfoResponseDto saveWork(ToDoSaveRequestDto todoSaveRequestDto) { //일정 추가
        Soon soon = soonRepository.findById(todoSaveRequestDto.getSoonId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 존재하지 않습니다."));

        ToDo todo = ToDo.builder()
                .soon(soon)
                .work(todoSaveRequestDto.getWork())
                .build();

        todoRepository.save(todo);

        return ToDoInfoResponseDto.from(todo);
    }

    @Transactional(readOnly = true)
    public ToDoInfoResponseDto getWork(Long workId) { //요청받은 일정 조회
        ToDo todo = todoRepository.findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 내용의 일정을 찾을 수 없습니다."));

        return ToDoInfoResponseDto.from(todo);
    }

    @Transactional
    public ToDoInfoResponseDto updateWork(Long workId, ToDoSaveRequestDto todoSaveRequestDto) { //일정 수정
        ToDo todo = todoRepository.findById(workId) //할 일 아이디로 할 일 찾기..?
                .orElseThrow(() -> new IllegalArgumentException("요청하신 내용의 일정을 찾을 수 없습니다."));

        Soon soon = soonRepository.findById(todoSaveRequestDto.getSoonId()) //급한 일정 아이디로 할 일 찾기
                .orElseThrow(() -> new IllegalArgumentException("요청하신 내용의 일정을 찾을 수 없습니다."));

        todo.update(todoSaveRequestDto.getWork(), soon); //변경된 내용 덮어쓰기?

        return ToDoInfoResponseDto.from(todo); //내용 변경된 상태로 반환?
    }

    @Transactional
    public void deleteWork(Long workId) {
        todoRepository.deleteById(workId);
    } //일정 삭제

    @Transactional(readOnly = true)
    public List<ToDoInfoResponseDto> getAllWork() { //전체 일정 조회
        return todoRepository.findAll()
                .stream()
                .map(ToDoInfoResponseDto::from)
                .toList();
    }
}
