package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sistemalima.adopet.user.entity.UserEntity
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class UserRequestDTO(
    @JsonProperty("nome")
    @field:NotBlank(message = "nome não pode ser nulo ou vazio")
    @field:Size(max = 50, message = "nome pode ter no maximo 50 caracter")
    val name: String,

    @JsonProperty("email")
    @field:NotBlank(message = "email não pode ser nulo ou vazio")
    @field:Size(max = 50, message = "email pode ter no maximo 50 caracter")
    @field:Email
    val email: String,

    @JsonProperty("senha")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(min = 8, max = 8, message = "senha pode ter no maximo 8 caracter e minimo 8 caracter")
    val password: String
) {
    fun toModel(): UserEntity {
        return UserEntity(
            name = this.name.lowercase(),
            email = this.email.lowercase(),
            password = this.password.lowercase()
        )
    }
}
