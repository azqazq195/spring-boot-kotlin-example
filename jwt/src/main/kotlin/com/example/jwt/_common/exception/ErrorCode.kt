package com.example.jwt._common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    // auth
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 만료되었습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),

    // user
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),
}