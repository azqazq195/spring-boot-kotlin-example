package com.example.jwt.user.application.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class DuplicatedEmailException : ApiException(ErrorCode.DUPLICATED_EMAIL)