package com.sistemalima.adopet.tutor.service.impl

import com.sistemalima.adopet.tutor.controller.dto.TutorResponseDTO
import com.sistemalima.adopet.tutor.entity.TutorEntity
import com.sistemalima.adopet.tutor.exceptions.BusinessException
import com.sistemalima.adopet.tutor.exceptions.enum.RegrasTecnicaEnum
import com.sistemalima.adopet.tutor.repository.TutorRepository
import com.sistemalima.adopet.tutor.service.TutorService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TutorServiceImpl(
    private val tutorRepository: TutorRepository
): TutorService {

    private val logger = LoggerFactory.getLogger(TutorServiceImpl::class.java)

    @Transactional
    override fun create(tutor: TutorEntity): TutorResponseDTO {
        logger.info("Cadastrando um tutor, $tag, method: create")

        try {
            val tutorEntity = tutorRepository.save(tutor)

            logger.info("Tutor salvo com sucesso, $tag, method: create")

            return TutorResponseDTO(tutorEntity)
        } catch (exception: Exception) {
            logger.error(String.format("Error: $messageBusinessException, $tag, method: create," +
                    " exception: $exception"))
            throw BusinessException(messageBusinessException, RegrasTecnicaEnum.FALHA_DE_NEGOCIO, exception)
        }
    }
    @Transactional(readOnly = true)
    override fun findById(id: Long): TutorResponseDTO {

        logger.info("Buscando id do tutor na base, $tag, method: findById")

        val tutorEntity = tutorRepository.findById(id).orElseThrow {

            logger.error(String.format("Error: $messageNotFound id: $id, $tag, method: findById"))

            throw BusinessException(messageNotFound, RegrasTecnicaEnum.NAO_ENCONTRADO)
        }

        return TutorResponseDTO(tutorEntity)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<TutorResponseDTO> {

        logger.info(String.format("Listando os tutores, $tag, method: findAll"))

        val listaEntity = tutorRepository.findAll()

        return listaEntity.map { it -> TutorResponseDTO(it) }
    }

    companion object {
        private const val tag = "class: TutorServiceImpl"
        private const val messageBusinessException = "Houve uma falha de negocio"
        private const val messageNotFound = "Id do tutor nao encontrado"
    }
}