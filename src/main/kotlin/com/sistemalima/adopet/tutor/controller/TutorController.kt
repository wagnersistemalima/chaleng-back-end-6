package com.sistemalima.adopet.tutor.controller

import com.sistemalima.adopet.tutor.controller.dto.Request
import com.sistemalima.adopet.tutor.controller.dto.TutorRequestDTO
import com.sistemalima.adopet.tutor.controller.dto.TutorResponseDTO
import com.sistemalima.adopet.tutor.service.TutorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
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
    fun create(@RequestBody @Valid request: Request<TutorRequestDTO>): ResponseEntity<TutorResponseDTO> {

        logger.info(String.format("Inicio do cadastro de um tutor, $tag, method: create"))

        val tutorRequestDTO = request.data
        val tutor = tutorRequestDTO.toModel()
        val tutorResponse = tutorService.create(tutor)

        logger.info(String.format("Fim do processo de cadastro de um tutor, $tag, method: create"))

        return ResponseEntity.ok(tutorResponse)
    }

    companion object {
        private const val tag = "class: TutorController"
    }
}