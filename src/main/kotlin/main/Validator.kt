package main

import main.exception.InvalidNickname
import main.exception.NicknameAlreadyUsed

class Validator {
    fun validateNickname(nickname: String) {
        if(nickname.isNullOrEmpty() || nickname.isBlank()) {
            throw InvalidNickname()
        }
    }
}
