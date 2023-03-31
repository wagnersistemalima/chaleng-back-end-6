package com.sistemalima.adopet.tutor.service

import com.sistemalima.adopet.tutor.controller.dto.TutorResponseDTO
import com.sistemalima.adopet.tutor.controller.dto.TutorUpdateDTO
import com.sistemalima.adopet.tutor.entity.TutorEntity

interface TutorService {

    fun create(tutor: TutorEntity): TutorResponseDTO
    fun findById(id: Long): TutorResponseDTO
    fun findAll(): List<TutorResponseDTO>

    fun fullUpdate(id: Long, tutor: TutorEntity): TutorResponseDTO
    fun incrementalUpdate(id: Long, tutorUpdateDTO: TutorUpdateDTO): TutorResponseDTO
}