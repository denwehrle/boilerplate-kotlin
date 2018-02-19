package com.denwehrle.boilerplate.data.manager.login

import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper
import com.denwehrle.boilerplate.data.manager.base.BaseDataManager
import com.denwehrle.boilerplate.data.remote.model.LoginResult
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The [LoginDataManager] is the single point of communication between the different layers.
 *
 * @author Dennis Wehrle
 */
@Singleton
class LoginDataManager @Inject constructor(preferenceHelper: PreferenceHelper) : BaseDataManager(preferenceHelper) {

    fun signIn(username: String, password: String): Single<LoginResult> {
        return Single.just(LoginResult.SUCCESS)
    }
}