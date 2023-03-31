package com.sistemalima.adopet.user.controller.handler

import com.sistemalima.adopet.user.controller.dto.Response
import com.sistemalima.adopet.user.controller.handler.dto.ErrorView
import com.sistemalima.adopet.user.exceptions.BadRequestException
import com.sistemalima.adopet.user.exceptions.BusinessException
import com.sistemalima.adopet.user.exceptions.ResourceNotFoundException
import com.sistemalima.adopet.user.exceptions.enum.RegrasTecnicaEnum
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class RestExceptionHandler {

    private val logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(exception: BadRequestException, request: HttpServletRequest): Response<ErrorView> {

        logger.info(
            "ERROR: $tag, method: handleBadRequest, " +
                    "status: ${HttpStatus.BAD_REQUEST.value()}, " +
                    "error: ${HttpStatus.BAD_REQUEST.name} " +
                    "message: ${exception.message}"
        )

        return Response(
            data = ErrorView(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                message = exception.message ?: messageBadRequestException,
                path = request.servletPath
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): Response<ErrorView> {

        logger.info(
            "ERROR: $tag, method: handleValidationError, " +
                    "status: ${HttpStatus.BAD_REQUEST.value()}, " +
                    "error: ${HttpStatus.BAD_REQUEST.name} " +
                    "message: ${exception.message}"
        )

        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { error -> errorMessage[error.field] = error.defaultMessage }
        return Response(
            data = ErrorView(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                message = errorMessage.toString(),
                path = request.servletPath
            )
        )
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(exception: BusinessException, request: HttpServletRequest): Response<ErrorView> {

        when (exception.regrasTecnicaEnum) {

            RegrasTecnicaEnum.FALHA_DE_NEGOCIO -> {

                logger.info(
                    "ERROR: $tag, method: handleBusinessException, " +
                            "status: ${HttpStatus.BAD_REQUEST.value()}, " +
                            "error: ${HttpStatus.BAD_REQUEST.name} " +
                            "message: ${exception.message}"
                )

                return Response(
                    data = ErrorView(
                        status = HttpStatus.BAD_REQUEST.value(),
                        error = HttpStatus.BAD_REQUEST.name,
                        message = exception.message ?: messageBadRequestException,
                        path = request.servletPath
                    )
                )
            }
        }
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception, request: HttpServletRequest): Response<ErrorView> {

        logger.info(
            "ERROR: $tag, method: handleException, " +
                    "status: ${HttpStatus.INTERNAL_SERVER_ERROR.value()}, " +
                    "error: ${HttpStatus.INTERNAL_SERVER_ERROR.name} " +
                    "message: ${exception.message}"
        )

        return Response(
            data = ErrorView(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = messageInternalServerError,
                path = request.servletPath
            )
        )
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException, request: HttpServletRequest): Response<ErrorView> {

        logger.info(
            "ERROR: $tag, method: ResourceNotFoundException, " +
                    "status: ${HttpStatus.NOT_FOUND.value()}, " +
                    "error: ${HttpStatus.NOT_FOUND.name} " +
                    "message: ${exception.message}"
        )

        return Response(
            data = ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = exception.message?: "Entity not found",
                path = request.servletPath
            )
        )
    }

    companion object {
        private const val tag = "class: RestExceptionHandler"
        private const val messageBadRequestException = "Configurações de sistema definidas incorretamente ou entradas irregulares nos elementos do sistema"
        private const val messageInternalServerError = "Ocorreu um erro interno no servidor, contate o administrador"
    }
}