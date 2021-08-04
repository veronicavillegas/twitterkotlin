package com.twitterkata.domain.users.requestData

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserData(@JsonProperty("firstname") val firstname: String,
                          @JsonProperty("firstname") val surname: String) {

}
