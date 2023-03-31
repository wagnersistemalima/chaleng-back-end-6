package com.sistemalima.adopet.tutor.controller

import com.sistemalima.adopet.tutor.controller.dto.*
import com.sistemalima.adopet.tutor.service.TutorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
class TutorController(
    @Autowired
    private val tutorService: TutorService
) {

    private val logger = LoggerFactory.getLogger(TutorController::class.java)

    @PostMapping
    fun create(@RequestBody @Valid request: Request<TutorRequestDTO>): ResponseEntity<Response<TutorResponseDTO>> {

        logger.info(String.format("[POST] Inicio do cadastro de um tutor, $tag, method: create"))

        val tutorRequestDTO = request.data
        val tutor = tutorRequestDTO.toModel()
        val tutorResponse = tutorService.create(tutor)
        val response = Response(tutorResponse)

        logger.info(String.format("[POST] Fim do processo de cadastro de um tutor, $tag, method: create"))

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Response<TutorResponseDTO>> {

        logger.info(String.format("[GET] Inicio da busca por id: $id, de um tutor, $tag, method: findById"))

        val tutorResponse = tutorService.findById(id)
        val response = Response(tutorResponse)

        logger.info(String.format("[GET] Fim da busca por id: $id, de um tutor, $tag, method: findById"))

        return ResponseEntity.ok(response)

    }

    @GetMapping
    fun findAll(): ResponseEntity<Response<List<TutorResponseDTO>>> {

        logger.info(String.format("[GET] Inicio da listagem dos tutores, $tag, method: findById"))

        val lista = tutorService.findAll()

        val response = Response(lista)

        logger.info(String.format("[GET] Fim da listagem dos tutores, $tag, method: findById"))

        return ResponseEntity.ok(response)

    }

    @PutMapping("/{id}")
    fun fullUpdate(@PathVariable("id") id: Long, @RequestBody @Valid request: Request<TutorRequestDTO>): ResponseEntity<Response<TutorResponseDTO>> {

        logger.info(String.format("[PUT] Inicio da atualizacao do cadastro de um tutor, $tag, method: fullUpdate"))

        val tutorRequestDTO = request.data

        val tutor = tutorRequestDTO.toModel()

        val tutorResponse = tutorService.fullUpdate(id, tutor)

        val response = Response(tutorResponse)

        logger.info(String.format("[PUT] Finalizando a atualizacao do cadastro de um tutor, $tag, method: fullUpdate"))

        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdate(@PathVariable("id") id: Long, @RequestBody @Valid request: Request<TutorUpdateDTO>): ResponseEntity<Response<TutorResponseDTO>> {

        logger.info(String.format("[PATCH] Inicio da atualizacao do cadastro de um tutor, $tag, method: incrementalUpdate"))

        val tutorUpdateDTO = request.data

        val tutorResponse = tutorService.incrementalUpdate(id, tutorUpdateDTO)

        val response = Response(tutorResponse)

        logger.info(String.format("[PATCH] Finalizando a atualizacao do cadastro de um tutor, $tag, method: incrementalUpdate"))

        return ResponseEntity.ok(response)
    }

    companion object {
        private const val tag = "class: TutorController"
    }
}