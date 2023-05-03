package com.example.jwt._common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    /*
     * AUTH
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),

    // refresh token 으로 token 을 찾을 수 없음.
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),

    // access token 이 발급시점과 다름.
    NOT_MATCH_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "인증정보가 만료되었습니다."),

    /*
     * USER
     */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),
}