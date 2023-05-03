package com.example.jwt.user.application.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class NotMatchPasswordException : ApiException(ErrorCode.NOT_MATCH_PASSWORD)
