package com.twitterkata.infraestructure

import java.util.*

class UUIDGenerator : IDGenerator{
    override fun generateId() = UUID.randomUUID().toString()
}