package com.twitterkata.actions.user_account

enum class Messages(val message: String) {
    OK("Ok"),
    NICKNAME_ALREADY_EXISTENT("Nickname already existent"),
    INVALID_NICKNAME("Invalid nickname"),
    INEXISTENT_USER("Inexistent user")
}

enum class Status(val status: String) {
    OK("ok"),
    FAIL("fail")
}