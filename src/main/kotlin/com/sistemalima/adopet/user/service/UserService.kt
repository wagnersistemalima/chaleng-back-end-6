package com.sistemalima.adopet.user.service

import com.sistemalima.adopet.user.controller.dto.UserFullUpdateDTO
import com.sistemalima.adopet.user.controller.dto.UserResponseDTO
import com.sistemalima.adopet.user.controller.dto.UserIncrementalUpdateDTO
import com.sistemalima.adopet.user.entity.UserEntity

interface UserService {

    fun create(user: UserEntity): UserResponseDTO
    fun findById(id: Long): UserResponseDTO
    fun findAll(): List<UserResponseDTO>

    fun fullUpdate(id: Long, userFullUpdateDTO: UserFullUpdateDTO): UserResponseDTO
    fun incrementalUpdate(id: Long, userIncrementalUpdateDTO: UserIncrementalUpdateDTO): UserResponseDTO
    fun delete(id: Long)
}