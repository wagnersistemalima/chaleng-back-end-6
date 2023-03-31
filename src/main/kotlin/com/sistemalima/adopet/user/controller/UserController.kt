package com.sistemalima.adopet.user.controller

import com.sistemalima.adopet.user.controller.dto.*
import com.sistemalima.adopet.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/tutores"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    @Autowired
    private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping
    fun create(@RequestBody @Valid request: Request<UserRequestDTO>): ResponseEntity<Response<UserResponseDTO>> {

        logger.info(String.format("[POST] Inicio do cadastro de um usuario, $tag, method: create"))

        val userRequestDTO = request.data
        val user = userRequestDTO.toModel()
        val userResponse = userService.create(user)
        val response = Response(userResponse)

        logger.info(String.format("[POST] Fim do processo de cadastro de um usuario, $tag, method: create"))

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Response<UserResponseDTO>> {

        logger.info(String.format("[GET] Inicio da busca por id: $id, de um usuario, $tag, method: findById"))

        val userResponse = userService.findById(id)
        val response = Response(userResponse)

        logger.info(String.format("[GET] Fim da busca por id: $id, de um usuario, $tag, method: findById"))

        return ResponseEntity.ok(response)

    }

    @GetMapping
    fun findAll(): ResponseEntity<Response<List<UserResponseDTO>>> {

        logger.info(String.format("[GET] Inicio da listagem dos usuario, $tag, method: findById"))

        val lista = userService.findAll()

        val response = Response(lista)

        logger.info(String.format("[GET] Fim da listagem dos usuario, $tag, method: findById"))

        return ResponseEntity.ok(response)

    }

    @PutMapping("/{id}")
    fun fullUpdate(@PathVariable("id") id: Long, @RequestBody @Valid request: Request<UserRequestDTO>): ResponseEntity<Response<UserResponseDTO>> {

        logger.info(String.format("[PUT] Inicio da atualizacao do cadastro de um usuario, $tag, method: fullUpdate"))

        val userRequestDTO = request.data

        val user = userRequestDTO.toModel()

        val tutorResponse = userService.fullUpdate(id, user)

        val response = Response(tutorResponse)

        logger.info(String.format("[PUT] Finalizando a atualizacao do cadastro de um usuario, $tag, method: fullUpdate"))

        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdate(@PathVariable("id") id: Long, @RequestBody @Valid request: Request<UserUpdateDTO>): ResponseEntity<Response<UserResponseDTO>> {

        logger.info(String.format("[PATCH] Inicio da atualizacao do cadastro de um usuario, $tag, method: incrementalUpdate"))

        val userUpdateDTO = request.data

        val userResponse = userService.incrementalUpdate(id, userUpdateDTO)

        val response = Response(userResponse)

        logger.info(String.format("[PATCH] Finalizando a atualizacao do cadastro de um usuario, $tag, method: incrementalUpdate"))

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Unit> {

        logger.info(String.format("[DELETE] Inicio do cancelamento do cadastro de um usuario, $tag, method: delete"))

        userService.delete(id)

        logger.info(String.format("[DELETE] Fim do cancelamento do cadastro de um usuario, $tag, method: delete"))

        return ResponseEntity.noContent().build()

    }

    companion object {
        private const val tag = "class: UserController"
    }
}