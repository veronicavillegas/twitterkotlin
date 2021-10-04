package com.twitterkata.api

import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.users.actions.GetUser
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser

class FactoryActions {

    fun getRegisterUserAction() = RegisterUser(FactoryRepos.getUserRepository())

    fun getUpdateUserAction() = UpdateUser(FactoryRepos.getUserRepository())

    fun getUserAction() = GetUser(FactoryRepos.getUserRepository())

    fun getFollowUserAction() = FollowUser(FactoryRepos.getUserRepository(), FactoryRepos.getFollowerRepository())
}

