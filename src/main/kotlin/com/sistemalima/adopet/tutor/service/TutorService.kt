package com.sistemalima.adopet.tutor.service

import com.sistemalima.adopet.tutor.controller.dto.TutorResponseDTO
import com.sistemalima.adopet.tutor.entity.TutorEntity

interface TutorService {

    fun create(tutor: TutorEntity): TutorResponseDTO
}