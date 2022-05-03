package com.sparta.neonaduri_back.controller;

/**
 * [controller] - userController
 *
 * @class   : userController
 * @author  : 오예령
 * @since   : 2022.04.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2022.05.03 오예령       아이디 중복검사 및 로그인 정보 조회 기능 추가
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.neonaduri_back.dto.user.DuplicateCheckDto;
import com.sparta.neonaduri_back.dto.user.IsLoginDto;
import com.sparta.neonaduri_back.dto.user.KakaoUserInfoDto;
import com.sparta.neonaduri_back.dto.user.SignupRequestDto;
import com.sparta.neonaduri_back.security.UserDetailsImpl;
import com.sparta.neonaduri_back.service.GoogleLoginService;
import com.sparta.neonaduri_back.service.KakaoUserService;
import com.sparta.neonaduri_back.service.UserService;
import com.sparta.neonaduri_back.utils.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final KakaoUserService kakaoUserService;
    private final GoogleLoginService googleLoginService;
    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDto signupRequestDto, Errors errors) {
        String message = userService.registerUser(signupRequestDto, errors);
        if (message.equals("회원가입 성공")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 카카오 로그인
    @GetMapping("/user/kakao/callback")
    public KakaoUserInfoDto kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }

    // 구글 로그인 API
    @GetMapping("/user/google/callback")
    public void googleLogin(
            @RequestParam String code,
            HttpServletResponse response
    ) throws JsonProcessingException {
        googleLoginService.googleLogin(code, response);
    }

    // 아이디 중복검사
    @PostMapping("/api/idcheck")
    public ResponseEntity<StatusMessage> idcheck(@RequestBody DuplicateCheckDto duplicateCheckDto) {
        StatusMessage statusMessage = new StatusMessage();
        HashMap<String, String> hashMap = userService.idDuplichk(duplicateCheckDto.getUserName());
        if (hashMap.get("status").equals("OK")) {
            statusMessage.setStatus(StatusMessage.StatusEnum.OK);
            return new ResponseEntity<>(statusMessage, HttpStatus.OK);
        } else {
            statusMessage.setStatus(StatusMessage.StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // 유저 정보 확인
    @GetMapping("/api/islogin")
    private ResponseEntity<IsLoginDto> isloginChk(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.isloginChk(userDetails);
        return new ResponseEntity<>(userService.isloginChk(userDetails), HttpStatus.OK);
    }
}

//테스트테스트