package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class UserFullUpdateDTO(

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
    val password: String,

    @JsonProperty("url_foto_perfil")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    val profilePictureUrl: String,

    @JsonProperty("descricao")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(max = 200, message = "a descricao pode ter no maximo 200 caracter")
    val about: String,

    @JsonProperty("telefone")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(min = 11, max = 11, message = "numero de telefone pode ter no maximo 11 caracter e minimo 11 caracter com ddd")
    val phone: String

) {
}
