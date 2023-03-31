package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class Request<T>(
    @JsonProperty("request")
    @field:NotNull
    @field:Valid
    val data: T
)
