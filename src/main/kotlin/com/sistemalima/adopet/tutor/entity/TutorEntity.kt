package com.sistemalima.adopet.tutor.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "tb_tutor")
data class TutorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_unic_tutor")
    val id: Long? = null,
    @Column(name = "cod_nome_tutor")
    var name: String,
    @Column(name = "cod_email_tutor")
    var email: String,
    @Column(name = "cod_senha_tutor")
    var password: String
)
