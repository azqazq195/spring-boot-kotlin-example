package com.example.springbootkotlinexample.domain.user.user.entity

import com.example.springbootkotlinexample.common.entity.BaseTimeEntity
import com.example.springbootkotlinexample.domain.user.user.entity.constants.GenderEnum
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity(name = "tb_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        length = 255,
        nullable = false,
        unique = true,
    )
    @Comment("이메일")
    private val username: String,

    @Column(
        length = 255,
        nullable = false,
    )
    @Comment("비밀번호")
    private val password: String,

    @Column(
        length = 255,
    )
    @Comment("channel.io 에서 유저 구분 고유 아이디로 사용됨")
    val uuid: String? = null,

    @Column(
        length = 255,
    )
    @Comment("비밀번호 salt key")
    val salt: String? = null,

    @Column(
        length = 255,
    )
    @Comment("회원가입 이메일 인증 토큰")
    val verifyToken: String? = null,

    @Column(
        length = 10,
        nullable = false,
    )
    @Comment("이름")
    val name: String,

    @Column(
        length = 10,
        nullable = false,
    )
    @Comment("활동명")
    val nickName: String,

    @Column(
        length = 11,
        nullable = false,
    )
    @Comment("핸드폰번호")
    val phone: String,

    val address: String? = null,

    val addressDetail: String? = null,

    @Column(
        length = 1,
    )
    @Comment("성별")
    val gender: GenderEnum? = null,

    @Comment("생년월")
    val birthDay: Date? = null,

    @Comment("핸드폰 인증 여부")
    val isCheckedPhone: Boolean,

    @Comment("이메일 인증 여부")
    val isCheckedEmail: Boolean,

    @Comment("핸드폰 광고 수신 여부")
    val isReceivedPhone: Boolean,

    @Comment("이메일 광고 수신 여부")
    val isReceivedEmail: Boolean,

    @Column(
        length = 11,
    )
    @Comment("로그인 실패 횟수")
    val loginFailCount: Int,

    @Column(
        columnDefinition = "TEXT"
    )
    @Comment("탈퇴 사유")
    val withdrawReason: String? = null,

    @Comment("블럭 여부")
    val isBlocked: Boolean? = null,

    @Comment("마지막 로그인 일시")
    val lastLoginAt: Date? = null,

    @Column(
        columnDefinition = "TEXT"
    )
    @Comment("관리용 회원 비고 정보")
    val adminRemark: String? = null,
) : UserDetails, BaseTimeEntity() {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()
    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

    fun addLoginFailCount() = copy(loginFailCount = loginFailCount + 1)
    fun resetLoginFailCount() = copy(loginFailCount = 0)
    fun block() = copy(isBlocked = true)
    fun unblock() = copy(isBlocked = false)
    fun verifyEmail() = copy(isCheckedEmail = true)
    fun verifyPhone() = copy(isCheckedPhone = true)
    fun updatePassword(password: String, salt: String) = copy(password = password, salt = salt)
    fun updateLastLoginAt() = copy(lastLoginAt = Date())
}