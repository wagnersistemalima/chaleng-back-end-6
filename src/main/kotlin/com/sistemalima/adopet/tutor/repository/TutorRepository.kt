package com.sistemalima.adopet.tutor.repository

import com.sistemalima.adopet.tutor.entity.TutorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TutorRepository: JpaRepository<TutorEntity, Long> {
}