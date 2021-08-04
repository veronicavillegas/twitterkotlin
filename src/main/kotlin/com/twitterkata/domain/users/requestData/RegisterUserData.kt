package com.twitterkata.domain.users.requestData

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterUserData constructor(@JsonProperty("firstname") val firstname: String,
                                        @JsonProperty("surname") val surname: String,
                                        @JsonProperty("nickname") val nickname: String) {
}
