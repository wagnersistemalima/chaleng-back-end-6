package com.sistemalima.adopet.user.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Response<T>(
    @JsonProperty("response")
    val data: T
)
