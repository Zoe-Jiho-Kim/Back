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
import com.sparta.neonaduri_back.dto.user.SignupRequestDto;
import com.sparta.neonaduri_back.dto.user.SocialLoginInfoDto;
import com.sparta.neonaduri_back.security.UserDetailsImpl;
import com.sparta.neonaduri_back.service.GoogleLoginService;
import com.sparta.neonaduri_back.service.KakaoUserService;
import com.sparta.neonaduri_back.service.UserService;
import com.sparta.neonaduri_back.utils.StatusEnum;
import com.sparta.neonaduri_back.utils.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public SocialLoginInfoDto kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }

    // 구글 로그인
    @GetMapping("/user/google/callback")
    public void googleLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        googleLoginService.googleLogin(code, response);
    }

    // 아이디 중복검사
    @PostMapping("/api/idcheck")
    public ResponseEntity<StatusMessage> idcheck(@RequestBody DuplicateCheckDto duplicateCheckDto) {
        StatusMessage statusMessage = new StatusMessage();
        HashMap<String, String> hashMap = userService.idDuplichk(duplicateCheckDto.getUserName());
        if (hashMap.get("status").equals("OK")) {
            statusMessage.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(statusMessage, HttpStatus.OK);
        } else {
            statusMessage.setStatus(StatusEnum.BAD_REQUEST);
            return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // 유저 정보 확인
    @GetMapping("/api/islogin")
    private ResponseEntity<IsLoginDto> isloginChk(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.isloginChk(userDetails);
        return new ResponseEntity<>(userService.isloginChk(userDetails), HttpStatus.OK);
    }

    /*리팩토링 시 마이페이지 기능으로 묶어서(RequestMapping 사용) 정리*/
    // 회원정보 수정
    @PutMapping("/api/user/mypage")
    public ResponseEntity<StatusMessage> updateUserInfo(@RequestParam("profileImgFile") MultipartFile multipartFile,
                                                        @RequestParam String profileImgUrl,
                                                        @RequestParam("nickName") String nickName,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        Long userId=userDetails.getUser().getId();
        //파일이 비었다는 것은 사용자가 이미지를 삭제했다거나 , 사진 수정하지 않았다는 것
        if(multipartFile.isEmpty()){
            userService.deleteProfileImg(profileImgUrl,nickName,userId);
        }else{
            //사용자가 이미지를 수정함
            userService.updateUserInfo(multipartFile, nickName, userId);
        }

        StatusMessage message= new StatusMessage();

        message.setStatus(StatusEnum.OK);
        return new ResponseEntity<StatusMessage>(message,HttpStatus.OK);
    }

}