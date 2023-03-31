package com.sistemalima.adopet.tutor.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sistemalima.adopet.tutor.entity.TutorEntity
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class TutorUpdateDTO(

    @JsonProperty("senha")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(min = 8, max = 8, message = "senha pode ter no maximo 8 caracter e minimo 8 caracter")
    val password: String
) {
}
