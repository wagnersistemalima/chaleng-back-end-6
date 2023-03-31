package com.sistemalima.adopet.user.service.impl

import com.sistemalima.adopet.user.controller.dto.UserFullUpdateDTO
import com.sistemalima.adopet.user.controller.dto.UserIncrementalUpdateDTO
import com.sistemalima.adopet.user.controller.dto.UserResponseDTO
import com.sistemalima.adopet.user.entity.UserEntity
import com.sistemalima.adopet.user.exceptions.BusinessException
import com.sistemalima.adopet.user.exceptions.ResourceNotFoundException
import com.sistemalima.adopet.user.exceptions.enum.RegrasTecnicaEnum
import com.sistemalima.adopet.user.repository.UserRepository
import com.sistemalima.adopet.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    @Transactional
    override fun create(user: UserEntity): UserResponseDTO {
        logger.info("Cadastrando um usuario, $tag, method: create")

        try {
            val tutorEntity = userRepository.save(user)

            return UserResponseDTO(tutorEntity)
        } catch (exception: Exception) {
            logger.error(String.format("Error: $messageBusinessException, $tag, method: create," +
                    " exception: $exception"))
            throw BusinessException(messageBusinessException, RegrasTecnicaEnum.FALHA_DE_NEGOCIO, exception)
        }
    }
    @Transactional(readOnly = true)
    override fun findById(id: Long): UserResponseDTO {

        logger.info("Buscando id do usuario na base, $tag, method: findById")

        val userEntity = findByIdUserValidate(id)

        return UserResponseDTO(userEntity)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<UserResponseDTO> {

        logger.info(String.format("Listando os usuarios, $tag, method: findAll"))

        val listaEntity = userRepository.findAll()

        if(listaEntity.isEmpty()) {
            throw ResourceNotFoundException(messageNotFound)
        }

        return listaEntity.map { UserResponseDTO(it) }
    }
    @Transactional
    override fun fullUpdate(id: Long, userFullUpdateDTO: UserFullUpdateDTO): UserResponseDTO {
        logger.info("Atualizando o cadastro de um usuario, $tag, method: fullUpdate")

        val userEntity = findByIdUserValidate(id)

        val tutorEntityCopy = fullUpdateTutor(userFullUpdateDTO, userEntity)

        val entitySave = userRepository.save(tutorEntityCopy)

        return UserResponseDTO(entitySave)
    }

    @Transactional
    override fun incrementalUpdate(id: Long, userIncrementalUpdateDTO: UserIncrementalUpdateDTO): UserResponseDTO {
        logger.info("Atualizando o cadastro de um usuario, $tag, method: incrementalUpdate")

        val userEntity = findByIdUserValidate(id)

        val userEntityCopy = incrementalUpdateTutor(userEntity, userIncrementalUpdateDTO)

        val userSave = userRepository.save(userEntityCopy)

        return UserResponseDTO(userSave)
    }

    @Transactional
    override fun delete(id: Long) {

        logger.info("Cancelando o cadastro de um usuario, $tag, method: delete")

        val userEntity = findByIdUserValidate(id)

        userEntity.active = false
        userEntity.deleteAt = LocalDateTime.now()
        userRepository.save(userEntity)
    }

    @Transactional(readOnly = true)
    private fun findByIdUserValidate(id: Long): UserEntity {

        val userEntity = userRepository.findById(id).orElseThrow {

            logger.error(String.format("Error: $messageNotFound id: $id, $tag, method: findByIdUserValidate"))

            throw ResourceNotFoundException(messageNotFound)
        }

        validateUserActive(userEntity)

        return userEntity
    }

    private fun fullUpdateTutor(userFullUpdateDTO: UserFullUpdateDTO, userEntity: UserEntity): UserEntity {

        return userEntity.copy(
            name = userFullUpdateDTO.name,
            email = userFullUpdateDTO.email,
            password = userFullUpdateDTO.password,
            profilePictureUrl = userFullUpdateDTO.profilePictureUrl,
            about = userFullUpdateDTO.about,
            phone = userFullUpdateDTO.phone,
            active = true,
            updateAt = LocalDateTime.now()
        )
    }

    private fun incrementalUpdateTutor(userEntity: UserEntity, userIncrementalUpdateDTO: UserIncrementalUpdateDTO): UserEntity {

        return userEntity.copy(
            profilePictureUrl = userIncrementalUpdateDTO.profilePictureUrl,
            about = userIncrementalUpdateDTO.about,
            phone = userIncrementalUpdateDTO.phone,
            active = true,
            updateAt = LocalDateTime.now()
        )
    }

    private fun validateUserActive(userEntity: UserEntity) {
        if(!userEntity.active) {
            throw ResourceNotFoundException("Usuario inativo ou não encontrado")
        }
    }

    companion object {
        private const val tag = "class: UserServiceImpl"
        private const val messageBusinessException = "Houve uma falha de negocio"
        private const val messageNotFound = "Recurso nao encontrado"
    }
}