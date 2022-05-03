package com.sparta.neonaduri_back.dto.user;

/**
 * [dto] - [user] 카카오 로그인 유저 정보 KakaoUserInfoDto
 *
 * @class   : KakaoUserInfoDto
 * @author  : 오예령
 * @since   : 2022.05.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2022.05.03 오예령       dto 안에 user 패키지 만들어서 관련 class 합쳐놓음
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String userName;
    private String nickName;
    private String email;

    public KakaoUserInfoDto(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public KakaoUserInfoDto(String nickName) {
        this.nickName = nickName;
    }
}
