package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class UserIncrementalUpdateDTO(

    @JsonProperty("url_foto_perfil")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    val profilePictureUrl: String,

    @JsonProperty("descricao")
    @field:NotBlank(message = "senha não pode ser nula ou vazia")
    @field:Size(max = 200, message = "a descricao pode ter no maximo 200 caracter")
    val about: String,

    @JsonProperty("telefone")
    @field:NotBlank(message = "telefone não pode ser nula ou vazia")
    @field:Size(min = 11, max = 11, message = "numero de telefone pode ter no maximo 11 caracter e minimo 11 caracter com ddd")
    val phone: String,

    @JsonProperty("cidade")
    @field:NotBlank(message = "cidade não pode ser nula ou vazia")
    @field:Size(max = 50, message = "cidade pode ter no maximo 50 caracter")
    val city: String

) {
}
