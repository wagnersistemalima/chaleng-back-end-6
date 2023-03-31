package com.sistemalima.adopet.user.service

import com.sistemalima.adopet.user.controller.dto.UserResponseDTO
import com.sistemalima.adopet.user.controller.dto.UserUpdateDTO
import com.sistemalima.adopet.user.entity.UserEntity

interface UserService {

    fun create(user: UserEntity): UserResponseDTO
    fun findById(id: Long): UserResponseDTO
    fun findAll(): List<UserResponseDTO>

    fun fullUpdate(id: Long, user: UserEntity): UserResponseDTO
    fun incrementalUpdate(id: Long, userUpdateDTO: UserUpdateDTO): UserResponseDTO
    fun delete(id: Long)
}