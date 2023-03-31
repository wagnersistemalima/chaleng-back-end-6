package com.sistemalima.adopet.user.controller.dto

import com.sistemalima.adopet.user.entity.UserEntity
import java.time.LocalDateTime

data class UserResponseDTO(
    val id: Long? = null,
    val name: String,
    val email: String,
    val active: Boolean,
    val creatAt: String,
    val updateAt: String?
) {
    constructor(userEntity: UserEntity): this (userEntity.id, userEntity.name, userEntity.email, userEntity.active, userEntity.creatAt.toString(), userEntity.updateAt.toString())
}
