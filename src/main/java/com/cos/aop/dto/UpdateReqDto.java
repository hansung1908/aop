package com.cos.aop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateReqDto {

    @NotBlank(message = "비밀번호가 없습니다.")
    private String password;

    private String phone;
}
