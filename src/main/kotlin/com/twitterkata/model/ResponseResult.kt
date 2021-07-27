package com.twitterkata.model

import com.twitterkata.actions.user_account.enums.Messages
import com.twitterkata.actions.user_account.enums.Status

data class ResponseResult(val status: Status, val message: Messages) {
}
