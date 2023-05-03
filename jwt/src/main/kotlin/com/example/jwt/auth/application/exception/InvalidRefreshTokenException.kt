package com.example.jwt.auth.application.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class NotMatchAccessToken : ApiException(ErrorCode.NOT_MATCH_ACCESS_TOKEN)

