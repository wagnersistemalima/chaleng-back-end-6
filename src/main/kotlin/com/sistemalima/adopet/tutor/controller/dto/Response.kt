package com.sistemalima.adopet.tutor.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Response<T>(
    @JsonProperty("response")
    val data: T
)
