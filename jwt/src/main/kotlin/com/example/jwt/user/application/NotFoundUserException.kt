package com.example.jwt.user.application

class NotFoundUserException : RuntimeException(message) {
    companion object {
        private const val message = "존재하지 않는 사용자입니다."
    }
}