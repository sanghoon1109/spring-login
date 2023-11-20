package com.sparta.springlogin.Controller;

import com.sparta.springlogin.dto.LoginRequestDto;
import com.sparta.springlogin.dto.SignupRequestDto;
import com.sparta.springlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }
    @GetMapping("/card/doCard")
    public String doCard() {
        return "doCard";
    }

    @PostMapping("/user/loginMember")
    @ResponseBody
    public String loginMember(LoginRequestDto requestDto) {
        String idCheck = userService.checkUser(requestDto);
        if(idCheck.equals("success")) {
            String jsScript = "<script>";
            jsScript += "alert('" + requestDto.getUsername() + "님 환영합니다.');";
            jsScript += "location.href='/api/card/doCard?username=" + requestDto.getUsername() + "'";
            jsScript += "</script>";
            return jsScript;
        }
        else {
            String jsScript = "<script>";
            jsScript += "alert('아이디를 확인해주세요');";
            jsScript += "location.href='/'";
            jsScript += "</script>";
            return jsScript;
        }
    }

    @PostMapping("/user/signup")
    @ResponseBody
    public String signup(SignupRequestDto requestDto) {
        String check = userService.addUser(requestDto);
        if(check.equals("success")) {
            String jsScript = "<script>";
            jsScript += "alert('You are now a member.');";
            jsScript += "location.href='/api/user/login'";
            jsScript += "</script>";
            return jsScript;
        }
        else {

            String jsScript = "<script>";
            jsScript += "alert('아이디가 중복되었습니다.');";
            jsScript += "location.href='/'";
            jsScript += "</script>";
            return jsScript;
        }
    }

}
