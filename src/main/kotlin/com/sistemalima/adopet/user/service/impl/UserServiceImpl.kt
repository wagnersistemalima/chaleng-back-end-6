package com.sistemalima.adopet.user.service.impl

import com.sistemalima.adopet.user.controller.dto.UserResponseDTO
import com.sistemalima.adopet.user.controller.dto.UserUpdateDTO
import com.sistemalima.adopet.user.entity.UserEntity
import com.sistemalima.adopet.user.exceptions.BusinessException
import com.sistemalima.adopet.user.exceptions.ResourceNotFoundException
import com.sistemalima.adopet.user.exceptions.enum.RegrasTecnicaEnum
import com.sistemalima.adopet.user.repository.UserRepository
import com.sistemalima.adopet.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    override fun fullUpdate(id: Long, user: UserEntity): UserResponseDTO {
        logger.info("Atualizando o cadastro de um usuario, $tag, method: fullUpdate")

        val userEntity = findByIdUserValidate(id)

        val tutorEntityCopy = fullUpdateTutor(user, userEntity)

        val entitySave = userRepository.save(tutorEntityCopy)

        return UserResponseDTO(entitySave)
    }

    @Transactional
    override fun incrementalUpdate(id: Long, userUpdateDTO: UserUpdateDTO): UserResponseDTO {
        logger.info("Atualizando o cadastro de um usuario, $tag, method: incrementalUpdate")

        val userEntity = findByIdUserValidate(id)

        val userEntityCopy = incrementalUpdateTutor(userEntity, userUpdateDTO)

        val userSave = userRepository.save(userEntityCopy)

        return UserResponseDTO(userSave)
    }

    @Transactional
    override fun delete(id: Long) {

        logger.info("Cancelando o cadastro de um usuario, $tag, method: delete")

        val userEntity = findByIdUserValidate(id)

        userEntity.active = false
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

    @Transactional(readOnly = true)
    private fun findByIdUser(id: Long): UserEntity {

        val userEntity = userRepository.findById(id).orElseThrow {

            logger.error(String.format("Error: $messageNotFound id: $id, $tag, method: findByIdUser"))

            throw ResourceNotFoundException(messageNotFound)
        }

        return userEntity
    }

    private fun fullUpdateTutor(user: UserEntity, userEntity: UserEntity): UserEntity {

        return userEntity.copy(
            name = user.name,
            email = user.email,
            password = user.password,
            active = true
        )
    }

    private fun incrementalUpdateTutor(userEntity: UserEntity, userUpdateDTO: UserUpdateDTO): UserEntity {

        return userEntity.copy(
            password = userUpdateDTO.password,
            active = true
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