package com.sistemalima.adopet.user.exceptions

open class NoStacktraceException: RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, throwable: Throwable): super(message, throwable, true, false)
}