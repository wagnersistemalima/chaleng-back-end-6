package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sistemalima.adopet.user.entity.UserEntity
import java.time.LocalDateTime

data class UserResponseDTO(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("nome")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("url_foto_perfil")
    val profilePictureUrl: String?,
    @JsonProperty("descricao")
    val about: String?,
    @JsonProperty("telefone")
    val phone: String?,
    @JsonProperty("cidade")
    val city: String?,
    @JsonProperty("ativo")
    val active: Boolean,
    @JsonProperty("data_criacao")
    val creatAt: String,
    @JsonProperty("data_atualizacao")
    val updateAt: String?,
    @JsonProperty("data_cancelamento")
    val deleteAt: String?
) {
    constructor(userEntity: UserEntity): this (
        userEntity.id,
        userEntity.name,
        userEntity.email,
        userEntity.profilePictureUrl,
        userEntity.about, userEntity.phone, userEntity.city,
        userEntity.active, userEntity.creatAt.toString(),
        userEntity.updateAt.toString(), userEntity.deleteAt.toString())
}
