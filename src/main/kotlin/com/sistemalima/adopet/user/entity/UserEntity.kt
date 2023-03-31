package com.sistemalima.adopet.user.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "tb_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_unic_user")
    val id: Long? = null,
    @Column(name = "cod_nome_user")
    var name: String,
    @Column(name = "cod_email_user")
    var email: String,
    @Column(name = "cod_senha_user")
    var password: String,

    @Column(name = "cod_url_foto_perfil_user")
    var profilePictureUrl: String? = null,

    @Column(name = "cod_descricao_user")
    var about: String? = null,

    @Column(name = "cod_telefone_user")
    val phone: String? = null,

    @Column(name = "cod_ativo_user")
    var active: Boolean = true,
    @Column(name = "cod_data_criacao_user")
    val creatAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "cod_data_atualizacao_user")
    val updateAt: LocalDateTime? = null,
    @Column(name = "cod_data_cancelamento_user")
    var deleteAt: LocalDateTime? = null
)
