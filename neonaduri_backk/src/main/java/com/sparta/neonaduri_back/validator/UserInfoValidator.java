package com.sparta.neonaduri_back.validator;


import com.sparta.neonaduri_back.dto.post.MyLikePostDto;
import com.sparta.neonaduri_back.dto.post.MyLikeResponseDto;
import com.sparta.neonaduri_back.dto.user.SignupRequestDto;
import com.sparta.neonaduri_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Component
public class UserInfoValidator {

    private final UserRepository userRepository;


    public UserInfoValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getValidMessage(SignupRequestDto signupRequestDto, Errors errors) {

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = validateHandling(errors);
            return validatorResult.get("message");
        } else if (signupRequestDto.getUserName().contains(signupRequestDto.getPassword())) {
            return "비밀번호는 아이디를 포함할 수 없습니다.";
        } else if (userRepository.findByUserName(signupRequestDto.getUserName()).isPresent()) {
            return "중복된 아이디 입니다.";
        } else {
            return "회원가입 성공";
        }
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = "message";
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    //페이징 유효성 검사
    public Page<MyLikePostDto> overPages(List<MyLikePostDto> postList, int start, int end, Pageable pageable, int pageno) {

        Page<MyLikePostDto> pages = new PageImpl<>(postList.subList(start, end), pageable, postList.size());
        if(pageno > pages.getTotalPages()){
            throw new IllegalArgumentException("요청할 수 없는 페이지 입니다.");
        }
        return pages;
    }


}