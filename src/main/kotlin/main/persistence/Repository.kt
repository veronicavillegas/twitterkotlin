package main.persistence

import main.dto.Dto

abstract class Repository {
    abstract fun save(dto: Dto)
    abstract fun get(id: String): Dto?
    abstract fun update(id: String, data: Dto)
}