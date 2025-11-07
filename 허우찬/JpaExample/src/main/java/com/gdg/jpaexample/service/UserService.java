package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.User;
import com.gdg.jpaexample.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void createDefaultUsersIfNotFound() {

        if (!userRepository.existsByUsername("firstUser")) {
            User defaultUser1 = User.builder()
                    .username("firstUser")
                    .nickname("테스트유저")
                    .build();
            userRepository.save(defaultUser1);
            System.out.println("first user '테스트유저' (ID: " + defaultUser1.getId() + ") created.");
        }

        if (!userRepository.existsByUsername("secondUser")) {
            User defaultUser2 = User.builder()
                    .username("secondUser")
                    .nickname("테스트유저2")
                    .build();
            userRepository.save(defaultUser2);
            System.out.println("Default user '테스트유저2' (ID: " + defaultUser2.getId() + ") created.");
        }
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다. ID: " + userId));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
