package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.Dto

interface Repository {
    fun save(dto: Dto)
    fun get(id: String): Dto?
    fun update(id: String, data: Dto)
}