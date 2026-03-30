package com.green.greengram.application.user.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignInReq {
    //문자열은 @Size, 숫자는 @Min, @Max
    @Size(min=3, max=20, message = "아이디는 3~20자 사이만 가능합니다.")
    @NotNull(message = "아이디는 필수입니다.")
    private String uid;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{10,20}$", message = "비밀번호는 영문자, 숫자, 특수기호로 구성되며 10~20자 사이만 가능합니다")
    private String upw;
}
