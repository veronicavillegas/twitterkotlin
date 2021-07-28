package com.twitterkata.model

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status

data class ResponseResult(val status: Status, val message: Messages) {
}
