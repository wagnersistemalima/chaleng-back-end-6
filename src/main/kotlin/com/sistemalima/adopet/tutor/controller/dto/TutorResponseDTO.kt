package com.sistemalima.adopet.tutor.controller.dto

import com.sistemalima.adopet.tutor.entity.TutorEntity

data class TutorResponseDTO(
    val id: Long? = null,
    val name: String,
    val email: String,
) {
    constructor(tutorEntity: TutorEntity): this (tutorEntity.id, tutorEntity.name, tutorEntity.email)
}
