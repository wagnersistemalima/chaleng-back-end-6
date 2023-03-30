package com.sistemalima.adopet.tutor.exceptions

import com.sistemalima.adopet.tutor.exceptions.enum.RegrasTecnicaEnum

class BusinessException: NoStacktraceException {

    var regrasTecnicaEnum: RegrasTecnicaEnum = RegrasTecnicaEnum.FALHA_DE_NEGOCIO

    constructor(message: String, anRegrasTecnicaEnum: RegrasTecnicaEnum): super(message) {
        this.regrasTecnicaEnum = anRegrasTecnicaEnum
    }
    constructor(message: String, anRegrasTecnicaEnum: RegrasTecnicaEnum, throwable: Throwable): super(message, throwable) {
        this.regrasTecnicaEnum = anRegrasTecnicaEnum
    }

}