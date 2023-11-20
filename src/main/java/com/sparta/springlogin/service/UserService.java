package com.sparta.springlogin.service;

import com.sparta.springlogin.dto.LoginRequestDto;
import com.sparta.springlogin.dto.SignupRequestDto;
import com.sparta.springlogin.entity.User;
import com.sparta.springlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String addUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String passwd = requestDto.getPassword();
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            return "failed";
        }

        User user = new User(username, passwd);
        userRepository.save(user);
        return "success";
    }


    public String checkUser(LoginRequestDto requestDto) {
        System.out.println(requestDto.getUsername());
        System.out.println(requestDto.getPassword());
        Optional<User> checkUserId = userRepository.findAllByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword());
        if(checkUserId.isPresent()) {
            return "success";
        }
        else {
            return "failed";
        }
    }
}
