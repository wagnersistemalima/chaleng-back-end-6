package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class UserUpdateDTO(

    @JsonProperty("senha")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(min = 8, max = 8, message = "senha pode ter no maximo 8 caracter e minimo 8 caracter")
    val password: String
) {
}
