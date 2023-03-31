package com.sistemalima.adopet.user.exceptions

class BadRequestException: NoStacktraceException {

    constructor(message: String): super(message)

    constructor(message: String, throwable: Throwable): super(message, throwable)
}