package com.sparta.neonaduri_back.service;

/**
 * [Service] - 회원가입 Service
 *
 * @class   : UserService
 * @author  : 오예령
 * @since   : 2022.04.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2022.05.03 오예령       아이디 중복검사 및 로그인 정보 조회 기능 추가
 */


import com.sparta.neonaduri_back.dto.user.IsLoginDto;
import com.sparta.neonaduri_back.dto.user.SignupRequestDto;
import com.sparta.neonaduri_back.model.User;
import com.sparta.neonaduri_back.repository.UserRepository;
import com.sparta.neonaduri_back.security.UserDetailsImpl;
import com.sparta.neonaduri_back.validator.UserInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passWordEncoder;
    private final UserInfoValidator userInfoValidator;

    //회원가입
   @Transactional
    public String  registerUser(SignupRequestDto signupRequestDto, Errors errors){
        String message = userInfoValidator.getValidMessage(signupRequestDto, errors);
        if(message.equals("회원가입 성공")){
            String userName = signupRequestDto.getUserName();
            //비밀번호 암호화
            String passWordEncode = passWordEncoder.encode(signupRequestDto.getPassword());
            //저장할 유저 객체 생성
            User user = new User(userName, passWordEncode, signupRequestDto);
            //회원정보 저장
             userRepository.save(user);
             return "회원가입 성공";
        }else{
            return "회원가입 실패";
        }
    }

    // 아이디 중복체크
    public HashMap<String, String> idDuplichk(String userName){
       HashMap<String, String> hashMap = new HashMap<>();
       if (userRepository.findByUserName(userName).isPresent()){

           hashMap.put("status", "400");
//           hashMap.put("msg", "중복된 아이디입니다");
           return hashMap;
       } else{
           hashMap.put("status", "OK");
//           hashMap.put("msg", "사용가능한 아이디입니다");
           return hashMap;
       }

    }

    //로그인 확인
    public IsLoginDto isloginChk(UserDetailsImpl userDetails){
        System.out.println(userDetails.getUser().getUserName());
       String userName = userDetails.getUsername();
       String nickName = userDetails.getNickName();
       String profileImg = userDetails.getProfileImgUrl();
       int totalLike = userDetails.getTotalLike();

       Optional<User> user = userRepository.findByUserName(userName);
       IsLoginDto isLoginDto = IsLoginDto.builder()
               .userName(userName)
               .nickName(nickName)
               .profileImg(profileImg)
               .totalLike(totalLike)
               .build();
       return isLoginDto;
    }

}
