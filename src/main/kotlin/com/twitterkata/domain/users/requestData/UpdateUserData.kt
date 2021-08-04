package com.twitterkata.domain.users.requestData

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserData constructor(@JsonProperty("firstname") val firstname: String,
                                      @JsonProperty("surname") val surname: String) {
}
