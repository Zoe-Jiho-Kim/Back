package com.sparta.neonaduri_back.dto.user;

/**
 * [dto] - [user] 회원가입 SignupRequestDto
 *
 * @class   : SignupRequestDto
 * @author  : 오예령
 * @since   : 2022.05.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2022.05.03 오예령       dto 안에 user 패키지 만들어서 관련 class 합쳐놓음
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupRequestDto {
    private String userName;
    private String nickName;
    private String password;
    private String passwordCheck;
//    private String email;

}
