package com.sistemalima.adopet.user.controller.dto

import com.sistemalima.adopet.user.entity.UserEntity

data class UserResponseDTO(
    val id: Long? = null,
    val name: String,
    val email: String,
    val active: Boolean
) {
    constructor(userEntity: UserEntity): this (userEntity.id, userEntity.name, userEntity.email, userEntity.active)
}
