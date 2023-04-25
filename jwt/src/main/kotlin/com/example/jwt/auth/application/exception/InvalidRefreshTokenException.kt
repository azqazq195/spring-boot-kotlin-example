package com.example.jwt.auth.application.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class InvalidRefreshTokenException : ApiException(ErrorCode.INVALID_REFRESH_TOKEN)

