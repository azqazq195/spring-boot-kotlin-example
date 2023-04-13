package com.example.jwt.auth.application

class PasswordMatchException : RuntimeException(message) {
    companion object {
        private const val message = "비밀번호가 올바르지 않습니다."
    }
}