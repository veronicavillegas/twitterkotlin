package com.twitterkata.actions.user_account

import com.twitterkata.actions.user_account.exceptions.InvalidNickname
import com.twitterkata.actions.user_account.exceptions.NicknameAlreadyUsed
import com.twitterkata.model.User

class NicknameValidator {
    fun validateNickname(nickname: String, otherUserSameNickname: User?) {
        checkNickname(nickname)
        checkNicknameAlreadyUsed(otherUserSameNickname)
    }

    private fun checkNickname(nickname: String) {
        if (nickname.isNullOrEmpty() || nickname.isBlank()) {
            throw InvalidNickname()
        }
    }

    private fun checkNicknameAlreadyUsed(otherUserSameNickname: User?) {
        if (otherUserSameNickname != null) {
            throw NicknameAlreadyUsed()
        }
    }
}
