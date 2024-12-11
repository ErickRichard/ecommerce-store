package br.com.ecommerce.infrastructure.adapters.output.persistance.adapter

import br.com.ecommerce.application.ports.output.UserOutPutPort
import br.com.ecommerce.domain.model.User
import br.com.ecommerce.infrastructure.adapters.output.persistance.mapper.UserMapper
import br.com.ecommerce.infrastructure.adapters.output.persistance.repository.UserRepository

class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserOutPutPort {
    override fun save(user: User): User {
        val userEntity = userMapper.toUserEntity(user)
        val savedEntity = userRepository.save(userEntity)
        return userMapper.toDomain(savedEntity)
    }

    override fun getUserById(registration: Long): User {
        return userMapper.toDomain(userRepository.findByRegistration(registration))
    }
}