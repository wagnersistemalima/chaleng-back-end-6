package com.sistemalima.adopet.user.repository

import com.sistemalima.adopet.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findByActive(active: Boolean) : List<UserEntity>
}