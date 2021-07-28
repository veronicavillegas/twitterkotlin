package com.twitterkata.domain.enums

enum class Messages(val message: String) {
    OK("Ok"),
    NICKNAME_ALREADY_EXISTENT("Nickname already existent"),
    INVALID_NICKNAME("Invalid nickname"),
    INEXISTENT_USER("Inexistent user")
}