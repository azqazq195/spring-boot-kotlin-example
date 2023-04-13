package com.example.jwt.user.application

class ExistsEmailException : RuntimeException(message) {
    companion object {
        private const val message = "이미 존재하는 이메일 입니다."
    }
}