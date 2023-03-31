package com.sistemalima.adopet.user.exceptions

class ResourceNotFoundException: NoStacktraceException {

    constructor(message: String): super(message)

    constructor(message: String, throwable: Throwable): super(message, throwable)
}