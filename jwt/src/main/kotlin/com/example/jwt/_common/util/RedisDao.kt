package com.example.jwt._common.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisDao(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val redisBlackListTemplate: RedisTemplate<String, String>
) {
    fun set(key: String, value: Any, expire: Long) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMillis(expire))
    }

    fun get(key: String): Any {
        return redisTemplate.opsForValue().get(key) ?: throw RuntimeException("")
    }

    fun delete(key: String) {
        redisTemplate.delete(key)
    }

    fun setBlackList(key: String, value: String, expire: Long) {
        redisBlackListTemplate.opsForValue().set(key, value, Duration.ofMillis(expire))
    }

    fun hasKeyBlackList(key: String): Boolean {
        return redisBlackListTemplate.hasKey(key)
    }
}