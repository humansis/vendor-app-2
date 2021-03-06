package cz.quanti.android.vendor_app.main.authorization.viewmodel

import androidx.lifecycle.ViewModel
import cz.quanti.android.vendor_app.repository.login.LoginFacade
import cz.quanti.android.vendor_app.repository.utils.interceptor.HostUrlInterceptor
import cz.quanti.android.vendor_app.utils.ApiEnvironments
import cz.quanti.android.vendor_app.utils.CurrentVendor
import io.reactivex.Completable

class LoginViewModel(
    private val loginFacade: LoginFacade,
    private val hostUrlInterceptor: HostUrlInterceptor,
    private val currentVendor: CurrentVendor
) : ViewModel() {

    fun login(username: String, password: String): Completable {
        return loginFacade.login(username, password)
    }

    fun setApiHost(host: ApiEnvironments) {
        hostUrlInterceptor.setHost(host)
    }

    fun getSavedApiHost(): ApiEnvironments? {
        return currentVendor.url
    }

    fun isVendorLoggedIn(): Boolean {
        return currentVendor.isLoggedIn()
    }

    fun getCurrentVendorName(): String {
        return currentVendor.vendor.username
    }

    fun saveApiHost(host: ApiEnvironments) {
        currentVendor.url = host
    }
}
